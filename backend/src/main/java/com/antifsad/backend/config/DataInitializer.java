package com.antifsad.backend.config;
import com.antifsad.backend.model.Appointment;
import com.antifsad.backend.model.AppointmentStatus;
import com.antifsad.backend.model.AuditLog;
import com.antifsad.backend.model.Complaint;
import com.antifsad.backend.model.ComplaintStatus;
import com.antifsad.backend.model.ConsultationMode;
import com.antifsad.backend.model.InventoryItem;
import com.antifsad.backend.model.MedicalRecord;
import com.antifsad.backend.model.Prescription;
import com.antifsad.backend.model.PrescriptionItem;
import com.antifsad.backend.model.PrescriptionStatus;
import com.antifsad.backend.model.Role;
import com.antifsad.backend.model.SystemSetting;
import com.antifsad.backend.model.User;
import com.antifsad.backend.model.UserStatus;
import com.antifsad.backend.repository.AppointmentRepository;
import com.antifsad.backend.repository.AuditLogRepository;
import com.antifsad.backend.repository.ComplaintRepository;
import com.antifsad.backend.repository.InventoryItemRepository;
import com.antifsad.backend.repository.MedicalRecordRepository;
import com.antifsad.backend.repository.PrescriptionRepository;
import com.antifsad.backend.repository.SystemSettingRepository;
import com.antifsad.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Instant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(InventoryItemRepository inventoryItemRepository,
                               AppointmentRepository appointmentRepository,
                               PrescriptionRepository prescriptionRepository,
                               MedicalRecordRepository medicalRecordRepository,
                               UserRepository userRepository,
                               ComplaintRepository complaintRepository,
                               AuditLogRepository auditLogRepository,
                               SystemSettingRepository systemSettingRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            if (inventoryItemRepository.count() == 0) {
                InventoryItem paracetamol = new InventoryItem();
                setField(paracetamol, "name", "Paracetamol 500mg");
                setField(paracetamol, "stock", 120);
                setField(paracetamol, "price", BigDecimal.ZERO);
                setField(paracetamol, "usage", "Fever and mild pain relief");
                setField(paracetamol, "sideEffects", "Nausea, rash (rare)");
                setField(paracetamol, "typicalDosage", "500mg every 6 hours (max 4g/day)");
                setField(paracetamol, "lowStockThreshold", 20);
                if (paracetamol != null) {
                    inventoryItemRepository.save(paracetamol);
                }

                InventoryItem ibuprofen = new InventoryItem();
                setField(ibuprofen, "name", "Ibuprofen 400mg");
                setField(ibuprofen, "stock", 18);
                setField(ibuprofen, "price", BigDecimal.ZERO);
                setField(ibuprofen, "usage", "Pain, inflammation");
                setField(ibuprofen, "sideEffects", "Gastric irritation, dizziness");
                setField(ibuprofen, "typicalDosage", "400mg three times daily after food");
                setField(ibuprofen, "lowStockThreshold", 20);
                if (ibuprofen != null) {
                    inventoryItemRepository.save(ibuprofen);
                }

                InventoryItem amoxicillin = new InventoryItem();
                setField(amoxicillin, "name", "Amoxicillin 250mg");
                setField(amoxicillin, "stock", 6);
                setField(amoxicillin, "price", BigDecimal.ZERO);
                setField(amoxicillin, "usage", "Bacterial infections");
                setField(amoxicillin, "sideEffects", "Diarrhea, allergic reactions");
                setField(amoxicillin, "typicalDosage", "250-500mg every 8 hours as prescribed");
                setField(amoxicillin, "lowStockThreshold", 20);
                if (amoxicillin != null) {
                    inventoryItemRepository.save(amoxicillin);
                }
            }

            if (userRepository.count() == 0) {
                User admin = new User();
                setField(admin, "name", "Admin User");
                setField(admin, "email", "admin@antifsad.local");
                setField(admin, "passwordHash", passwordEncoder.encode("Password@123"));
                setField(admin, "role", Role.ADMIN);
                setField(admin, "status", UserStatus.ACTIVE);
                setField(admin, "isApproved", true);
                if (admin != null) {
                    userRepository.save(admin);
                }

                User pharmacist = new User();
                setField(pharmacist, "name", "Pharmacist User");
                setField(pharmacist, "email", "pharmacist@antifsad.local");
                setField(pharmacist, "passwordHash", passwordEncoder.encode("Password@123"));
                setField(pharmacist, "role", Role.PHARMACIST);
                setField(pharmacist, "status", UserStatus.ACTIVE);
                setField(pharmacist, "isApproved", true);
                if (pharmacist != null) {
                    userRepository.save(pharmacist);
                }

                User doctor = new User();
                setField(doctor, "name", "Doctor User");
                setField(doctor, "email", "doctor@antifsad.local");
                setField(doctor, "passwordHash", passwordEncoder.encode("Password@123"));
                setField(doctor, "role", Role.DOCTOR);
                setField(doctor, "status", UserStatus.ACTIVE);
                setField(doctor, "isApproved", true);
                if (doctor != null) {
                    userRepository.save(doctor);
                }

                User patient = new User();
                setField(patient, "name", "Patient User");
                setField(patient, "email", "patient@antifsad.local");
                setField(patient, "passwordHash", passwordEncoder.encode("Password@123"));
                setField(patient, "role", Role.PATIENT);
                setField(patient, "status", UserStatus.ACTIVE);
                setField(patient, "isApproved", true);
                if (patient != null) {
                    userRepository.save(patient);
                }

                User pendingDoctor = new User();
                setField(pendingDoctor, "name", "Pending Doctor");
                setField(pendingDoctor, "email", "pending.doctor@antifsad.local");
                setField(pendingDoctor, "passwordHash", passwordEncoder.encode("Password@123"));
                setField(pendingDoctor, "role", Role.DOCTOR);
                setField(pendingDoctor, "status", UserStatus.ACTIVE);
                setField(pendingDoctor, "isApproved", false);
                userRepository.save(pendingDoctor);

                User pendingPharmacist = new User();
                setField(pendingPharmacist, "name", "Pending Pharmacist");
                setField(pendingPharmacist, "email", "pending.pharmacist@antifsad.local");
                setField(pendingPharmacist, "passwordHash", passwordEncoder.encode("Password@123"));
                setField(pendingPharmacist, "role", Role.PHARMACIST);
                setField(pendingPharmacist, "status", UserStatus.ACTIVE);
                setField(pendingPharmacist, "isApproved", false);
                userRepository.save(pendingPharmacist);
            }

            User admin = userRepository.findByEmail("admin@antifsad.local").orElse(null);
            User doctor = userRepository.findByEmail("doctor@antifsad.local").orElse(null);
            User patient = userRepository.findByEmail("patient@antifsad.local").orElse(null);

            Appointment upcoming = null;
            Appointment completed = null;
            if (doctor != null && patient != null) {
                List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);

                if (appointments.isEmpty()) {
                    Appointment appointmentToSave = new Appointment();
                    setField(appointmentToSave, "patient", patient);
                    setField(appointmentToSave, "doctor", doctor);
                    setField(appointmentToSave, "date", LocalDate.now().plusDays(1));
                    setField(appointmentToSave, "time", LocalTime.of(10, 30));
                    setField(appointmentToSave, "mode", ConsultationMode.ONLINE);
                    setField(appointmentToSave, "reason", "Follow-up consultation for fever symptoms");
                    setField(appointmentToSave, "status", AppointmentStatus.SCHEDULED);
                    setField(appointmentToSave, "prescriptions", new ArrayList<>());
                    if (appointmentToSave != null) {
                        Appointment upcomingTemp = appointmentRepository.save(appointmentToSave);
                        if (upcomingTemp != null) {
                            upcoming = upcomingTemp;
                        }
                    }

                    Appointment completedAppointment = new Appointment();
                    setField(completedAppointment, "patient", patient);
                    setField(completedAppointment, "doctor", doctor);
                    setField(completedAppointment, "date", LocalDate.now().minusDays(2));
                    setField(completedAppointment, "time", LocalTime.of(15, 0));
                    setField(completedAppointment, "mode", ConsultationMode.ONLINE);
                    setField(completedAppointment, "reason", "General health review");
                    setField(completedAppointment, "status", AppointmentStatus.COMPLETED);
                    setField(completedAppointment, "prescriptions", new ArrayList<>());
                    Appointment completedTemp = null;
                    if (completedAppointment != null) {
                        completedTemp = appointmentRepository.save(completedAppointment);
                    }
                    if (completedTemp != null) {
                        completed = completedTemp;
                    }
                } else {
                    upcoming = appointments.stream()
                            .filter(appointment -> getField(appointment, "status", AppointmentStatus.class) == AppointmentStatus.SCHEDULED)
                            .findFirst()
                            .orElse(appointments.get(0));
                    completed = appointments.stream()
                            .filter(appointment -> getField(appointment, "status", AppointmentStatus.class) == AppointmentStatus.COMPLETED)
                            .findFirst()
                            .orElse(appointments.get(0));
                }
            }

            if (prescriptionRepository.count() == 0 && doctor != null && patient != null) {
                Prescription issued = new Prescription();
                setField(issued, "doctor", doctor);
                setField(issued, "patient", patient);
                setField(issued, "appointment", completed);
                setField(issued, "date", LocalDate.now().minusDays(2));
                setField(issued, "status", PrescriptionStatus.ISSUED);
                setField(issued, "notes", "Take medicine after food and stay hydrated.");

                PrescriptionItem item = new PrescriptionItem();
                setField(item, "prescription", issued);
                setField(item, "medicineName", "Paracetamol 500mg");
                setField(item, "dosage", "1 tablet twice daily");
                setField(item, "duration", "5 days");
                setField(item, "instructions", "After meals");
                setField(issued, "items", List.of(item));
                prescriptionRepository.save(issued);

                Prescription dispensed = new Prescription();
                setField(dispensed, "doctor", doctor);
                setField(dispensed, "patient", patient);
                setField(dispensed, "appointment", upcoming);
                setField(dispensed, "date", LocalDate.now().minusDays(10));
                setField(dispensed, "status", PrescriptionStatus.DISPENSED);
                setField(dispensed, "notes", "Complete the course.");

                PrescriptionItem dispensedItem = new PrescriptionItem();
                setField(dispensedItem, "prescription", dispensed);
                setField(dispensedItem, "medicineName", "Amoxicillin 250mg");
                setField(dispensedItem, "dosage", "1 capsule three times daily");
                setField(dispensedItem, "duration", "7 days");
                setField(dispensedItem, "instructions", "Do not skip doses");
                setField(dispensed, "items", List.of(dispensedItem));
                prescriptionRepository.save(dispensed);
            }

            if (medicalRecordRepository.count() == 0 && patient != null) {
                MedicalRecord bloodTest = new MedicalRecord();
                setField(bloodTest, "patient", patient);
                setField(bloodTest, "fileUrl", "https://example.com/reports/blood-test.pdf");
                setField(bloodTest, "description", "Blood test report");
                setField(bloodTest, "uploadedBy", "patient");
                setField(bloodTest, "date", LocalDate.now().minusDays(15));
                if (bloodTest != null) {
                    medicalRecordRepository.save(bloodTest);
                }

                MedicalRecord checkupSummary = new MedicalRecord();
                setField(checkupSummary, "patient", patient);
                setField(checkupSummary, "fileUrl", "https://example.com/reports/checkup-summary.pdf");
                setField(checkupSummary, "description", "Routine checkup summary");
                setField(checkupSummary, "uploadedBy", "admin");
                setField(checkupSummary, "date", LocalDate.now().minusDays(5));
                if (checkupSummary != null) {
                    medicalRecordRepository.save(checkupSummary);
                }
            }

            if (complaintRepository.count() == 0 && admin != null && patient != null && doctor != null) {
                Complaint complaint = new Complaint();
                setField(complaint, "createdByUser", patient);
                setField(complaint, "targetUser", doctor);
                setField(complaint, "relatedAppointment", upcoming);
                setField(complaint, "category", "Doctor conduct");
                setField(complaint, "subject", "Late consultation response");
                setField(complaint, "description", "Doctor joined the consultation late and the patient wants admin review.");
                setField(complaint, "status", ComplaintStatus.OPEN);
                setField(complaint, "assignedAdmin", admin);
                setField(complaint, "createdAt", Instant.now());
                setField(complaint, "updatedAt", Instant.now());
                complaintRepository.save(complaint);

                Complaint resolvedComplaint = new Complaint();
                setField(resolvedComplaint, "createdByUser", admin);
                setField(resolvedComplaint, "targetUser", patient);
                setField(resolvedComplaint, "relatedAppointment", completed);
                setField(resolvedComplaint, "category", "Support");
                setField(resolvedComplaint, "subject", "Profile correction request");
                setField(resolvedComplaint, "description", "Patient requested a correction in the uploaded profile details.");
                setField(resolvedComplaint, "status", ComplaintStatus.RESOLVED);
                setField(resolvedComplaint, "adminResponse", "Resolved after updating the profile.");
                setField(resolvedComplaint, "assignedAdmin", admin);
                setField(resolvedComplaint, "createdAt", Instant.now().minusSeconds(3600));
                setField(resolvedComplaint, "updatedAt", Instant.now().minusSeconds(3600));
                setField(resolvedComplaint, "resolvedAt", Instant.now().minusSeconds(3600));
                complaintRepository.save(resolvedComplaint);
            }

            if (systemSettingRepository.count() == 0) {
                systemSettingRepository.save(systemSetting("platformName", "AntiFSAD Health", "Platform display name"));
                systemSettingRepository.save(systemSetting("contactEmail", "support@antifsad.local", "Support email"));
                systemSettingRepository.save(systemSetting("supportPhone", "+91 90000 00000", "Support phone"));
                systemSettingRepository.save(systemSetting("workingHours", "09:00 - 18:00", "Support hours"));
                systemSettingRepository.save(systemSetting("termsText", "Platform terms managed by the administrator.", "Terms text"));
            }

            if (auditLogRepository.count() == 0 && admin != null) {
                auditLogRepository.save(auditLog(
                        admin,
                        "LOGIN_SUCCESS",
                        "AUTH",
                        "INFO",
                        "USER",
                        getField(admin, "id", Long.class),
                        "Seeded login history for admin"
                ));
                if (doctor != null) {
                    auditLogRepository.save(auditLog(
                            admin,
                            "ADMIN_DOCTOR_APPROVAL",
                            "APPROVAL",
                            "INFO",
                            "USER",
                            getField(doctor, "id", Long.class),
                            "Seeded approval audit event"
                    ));
                }
                auditLogRepository.save(auditLog(
                        admin,
                        "LOGIN_FAILED",
                        "AUTH",
                        "WARN",
                        "USER",
                        null,
                        "Seeded failed login attempt for dashboard review"
                ));
            }
        };
    }

    private static SystemSetting systemSetting(String key, String value, String description) {
        SystemSetting setting = new SystemSetting();
        setField(setting, "settingKey", key);
        setField(setting, "settingValue", value);
        setField(setting, "description", description);
        setField(setting, "updatedAt", Instant.now());
        return setting;
    }

    private static AuditLog auditLog(User actor,
                                     String action,
                                     String category,
                                     String severity,
                                     String targetType,
                                     Long targetId,
                                     String details) {
        AuditLog log = new AuditLog();
        setField(log, "actor", actor);
        setField(log, "action", action);
        setField(log, "category", category);
        setField(log, "severity", severity);
        setField(log, "targetType", targetType);
        setField(log, "targetId", targetId);
        setField(log, "details", details);
        setField(log, "createdAt", Instant.now());
        return log;
    }

    private static void setField(Object target, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        if (field == null) {
            throw new IllegalStateException("Field '" + fieldName + "' not found on " + target.getClass().getSimpleName());
        }
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, target, value);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getField(Object target, String fieldName, Class<T> type) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        if (field == null) {
            throw new IllegalStateException("Field '" + fieldName + "' not found on " + target.getClass().getSimpleName());
        }
        ReflectionUtils.makeAccessible(field);
        return (T) ReflectionUtils.getField(field, target);
    }
}
