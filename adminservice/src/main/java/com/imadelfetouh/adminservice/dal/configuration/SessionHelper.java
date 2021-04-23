package com.imadelfetouh.adminservice.dal.configuration;

import org.hibernate.Session;

public class SessionHelper {

    private Session session;

    public SessionHelper(SessionType sessionType) {
        if(sessionType.equals(SessionType.WRITE)) {
            this.session = SessionWriteConfiguration.getInstance().getSession();
        }
        else{
            this.session = SessionReadConfiguration.getInstance().getSession();
        }

        this.session.beginTransaction();
    }

    protected Session getSession() {
        return this.session;
    }

    protected void rollback() {
        if(this.session.isOpen()){
            this.session.getTransaction().rollback();
        }
    }

    protected void closeSession() {
        if(this.session.isOpen()) {
            this.session.close();
        }
    }
}
