/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deivydas.voroneckis.database;

import com.deivydas.voroneckis.database.IStudentDatabase;
import com.deivydas.voroneckis.domain.Room;
import com.deivydas.voroneckis.domain.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vdeiv
 */
public class SqlStudentDatabase implements IStudentDatabase {

    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private static EntityTransaction transaction;

    public SqlStudentDatabase(String name) throws DatabaseException {
        try {
            factory = Persistence.createEntityManagerFactory(name);
            manager = factory.createEntityManager();
            transaction = manager.getTransaction();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public void closeConnection() throws DatabaseException {
        try {
            manager.close();
            factory.close();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public List<Student> read() throws DatabaseException {
        try {
            Query query = manager.createQuery("select s from Student s"); //why select s
            List<Student> students = (List<Student>) query.getResultList();
            return students;
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Student student) throws DatabaseException {
        try {
            transaction.begin();
            manager.persist(student);
            manager.flush();
            transaction.commit();
            // return r.getId();
        } catch (Exception e) {
            transaction.rollback();
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws DatabaseException {
        try {
            transaction.begin();
            Student student = manager.find(Student.class, id);
            manager.remove(student);
            manager.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Student student) throws DatabaseException {
        try {
            transaction.begin();
            manager.merge(student);
            manager.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Student getStudentById(long id) {
        return manager.find(Student.class, id);
    }

}
