package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZI2-Zad5");
        EntityManager em = emf.createEntityManager();

        Random rand = new Random();

        Person p = new Person();
        p.setAge(26);
        p.setFirstName("Mikolaj");
        p.setFamilyName("Jozwik");
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();

        Person p1 = new Person();
        p1.setAge(1 + rand.nextInt(50));
        p1.setFirstName("Marcel");
        p1.setFamilyName("Grycki");
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        Person p2 = new Person();
        p2.setAge(1 + rand.nextInt(50));
        p2.setFirstName("Marcel");
        p2.setFamilyName("Raganski");
        em.getTransaction().begin();
        em.persist(p2);
        em.getTransaction().commit();

        Query q = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people = q.getResultList();
        for(Person prsn : people) {
            System.out.println(prsn.getFirstName() + " " + prsn.getFamilyName() + " " + prsn.getAge());
        }

        Query q2 = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people2 = q2.getResultList();
        for(Person prsn : people2) {
            if (prsn.getAge() < 18){
                prsn.setAge(18);
                em.getTransaction().begin();
                em.persist(prsn);
                em.getTransaction().commit();
            }
        }

        Query q3 = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people3 = q3.getResultList();
        for(Person prsn : people3) {
            System.out.println(prsn.getFirstName() + " " + prsn.getFamilyName() + " " + prsn.getAge());
        }


        Query q4 = em.createQuery("SELECT p FROM Person p WHERE p.age > :age");
        q4.setParameter("age", 25);
        List<Person> people4 = q4.getResultList();
        for(Person prsn : people4) {
            System.out.println(prsn.getFirstName() + " " + prsn.getFamilyName() + " " + prsn.getAge());
        }

    }
}