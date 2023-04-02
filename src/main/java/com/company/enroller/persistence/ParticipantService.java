package com.company.enroller.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Participant findByLogin(String login) {
		String hql = "FROM Participant WHERE login='" + login + "'";
		Query query = connector.getSession().createQuery(hql);
		return (Participant) query.uniqueResult();
	}

	public Participant add (Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
		return participant;
	}

	public Participant delete(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().remove(participant);
		transaction.commit();
		return participant;
	}

	public Participant update(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().update(participant);
		transaction.commit();
		return participant;
	}
}
