package com.example.langoal.repository;

import com.example.langoal.entities.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
}
