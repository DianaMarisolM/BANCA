package com.upload.crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.crud.Entity.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
