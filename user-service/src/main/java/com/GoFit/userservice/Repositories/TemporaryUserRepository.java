package com.GoFit.userservice.Repositories;

import com.GoFit.userservice.Models.TemporaryUser;
import com.GoFit.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.table.TableCellEditor;

public interface TemporaryUserRepository extends JpaRepository<TemporaryUser, Integer> {
    TemporaryUser findByEmail(String email);
}
