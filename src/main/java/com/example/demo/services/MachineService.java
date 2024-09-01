package com.example.demo.services;

import com.example.demo.entities.Machine;
import com.example.demo.repositories.MachineRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;

    public Machine createMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public Optional<Machine> getMachineById(Long id) {
        return machineRepository.findById(id);
    }

    public Machine updateMachine(Long id, Machine updatedMachine) {
        if (machineRepository.existsById(id)) {
            updatedMachine.setId(id);  // Устанавливаем ID для обновления
            return machineRepository.save(updatedMachine);
        }
        return null;
    }

    public void deleteMachine(Long id) {
        if (machineRepository.existsById(id)) {
            machineRepository.deleteById(id);
        }
    }
}
