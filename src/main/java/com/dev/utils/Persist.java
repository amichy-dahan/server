
package com.dev.utils;


import com.dev.objects.Employee;
import com.dev.objects.Price;
import com.dev.objects.Salary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Persist {

    private final SessionFactory sessionFactory;

    @Autowired
    public Persist(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public Employee getUserByEmail(String email) {
       Employee found = null;
        Session session = sessionFactory.openSession();
        found = (Employee) session.createQuery("FROM Employee WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
        session.close();
        return found;
    }

    public Employee getUserByToken(String token) {
        Employee found = null;
        Session session = sessionFactory.openSession();
        found = (Employee) session.createQuery("FROM Employee WHERE token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public Employee getEmployeeByEmployeeGmailAndToken(String email, String token) {
        Employee found = null;
        Session session = sessionFactory.openSession();
        found = (Employee) session.createQuery("FROM Employee WHERE email = :email AND token = :token")
                .setParameter("email", email)
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return found;
    }

    public void addEmployeeToDb(Employee employee) {
        Session session = sessionFactory.openSession();
        session.save(employee);
        session.close();
    }
    public void addPriceToDb(Price price) {
        Session session = sessionFactory.openSession();
        session.save(price);
        session.close();
    }
    public void addSalaryToDb(Salary salary) {
        Session session = sessionFactory.openSession();
        session.save(salary);
        session.close();
    }


//    public void addItemToDB(Item itemToAdd) {
//        Session session = sessionFactory.openSession();
//        session.save(itemToAdd);
//        session.close();
//
//
//    }
//
//
//    public List<Item> getAllItems() {
//        Session session = sessionFactory.openSession();
//        List<Item> items = session.createQuery("FROM Item ").list();
//        session.close();
//        return items;
//
//    }
//
//    public void addOfferToDb(Offer offerToAdd) {
//        Session session = sessionFactory.openSession();
//        session.save(offerToAdd);
//        session.close();
//
//    }
//
//    public void updateCredAfterOffer(String token, int userCredAfterOffer) {
//        User user = getUserByToken(token);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        user.setCred(userCredAfterOffer);
//        session.saveOrUpdate(user);
//        transaction.commit();
//        session.close();
//    }
//
    public void updatePrices(int employeeId,int priceOfHour , double priceOfKilo) {
        Price user = getUserById(employeeId);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        user.setPriceOfHour(priceOfHour);
        user.setPriceOfKilo(priceOfKilo);
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    public Price getUserById(int employeeId) {
        Price found = null;
        Session session = sessionFactory.openSession();
        found = (Price) session.createQuery("FROM Price WHERE employeeId = :employeeId")
                .setParameter("employeeId",employeeId)
                .uniqueResult();
        session.close();
        return found;
    }

    //
    public List<Salary> getAllSalary(int employeeId) {
        Session session = sessionFactory.openSession();
        List<Salary> salaries = session.createQuery("FROM Salary").list();
        session.close();
        List<Salary> employeeS= salaries.stream()
                .filter(salary -> salary.getEmployeeId() == employeeId).collect(Collectors.toList());
        return salaries;

    }
//
//    public void deleteOffers(List<Offer> offerListAfterFilterByItem) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        for (Offer i : offerListAfterFilterByItem) {
//            session.remove(i);
//        }
//        transaction.commit();
//        session.close();
//    }
//
//    public List<User> getAllUsers() {
//        Session session = sessionFactory.openSession();
//        List<User> users = session.createQuery("FROM User ").list();
//        session.close();
//        return users;
//    }
//
//    public void updateSysTotal(int payForSys) {
//        AdminSys adminSys = getById(1);
//        int temp;
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        temp = adminSys.getTotalBank() + payForSys;
//        adminSys.setTotalBank(temp);
//        session.saveOrUpdate(adminSys);
//        transaction.commit();
//        session.close();
//
//    }
//
//    public AdminSys getById(int id) {
//
//        AdminSys found = null;
//        Session session = sessionFactory.openSession();
//        found = (AdminSys) session.createQuery("FROM AdminSys WHERE id = :id")
//                .setParameter("id", id)
//                .uniqueResult();
//        session.close();
//        return found;
//    }
//    public Item getItemById(int id) {
//
//        Item found = null;
//        Session session = sessionFactory.openSession();
//        found = (Item)session.createQuery("FROM Item WHERE id = :id")
//                .setParameter("id", id)
//                .uniqueResult();
//        session.close();
//        return found;
//    }
//
//    public void updateItems(String closeOffer, int itemId) {
//        Item item = getItemById(itemId);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        item.setOpenOrClose(closeOffer);
//        session.saveOrUpdate(item);
//        transaction.commit();
//        session.close();
//
//
//    }
//
//
//
//
//    public Integer getOffersById(Integer itemId) {
//        Integer maxOffer = null;
//        Session session = sessionFactory.openSession();
//        List<Offer> offers = session.createQuery("FROM  Offer where itemId=:itemId order by amount DESC ")
//                .setParameter("itemId",itemId)
//                .list();
//
//        session.close();
//        if (!(offers.size()==0)){
//            maxOffer = offers.get(0).getAmount();
//        }
//        else {
//            maxOffer =0;
//        }
//        return maxOffer;
//    }
}

