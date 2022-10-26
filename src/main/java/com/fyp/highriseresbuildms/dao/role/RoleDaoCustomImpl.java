package com.fyp.highriseresbuildms.dao.role;

import com.fyp.highriseresbuildms.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class RoleDaoCustomImpl implements RoleDaoCustom{

    @PersistenceContext
    private EntityManager entityManager;

    protected final Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<Role> getSpecificRole(String test) {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
        Root<Role> root = criteria.from(Role.class);
        criteria.select(root).where(root.get("roleDescription").in(test));
        Query<Role> query = getSession().createQuery(criteria);
        List<Role> results = query.getResultList();
        return results;
    }
}
