package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Feedbackreply;
import model.Items;
import model.Users;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-01-20T10:36:13")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, Items> itemID;
    public static volatile SingularAttribute<Feedback, String> feedbackContent;
    public static volatile SingularAttribute<Feedback, Date> dateCreated;
    public static volatile SingularAttribute<Feedback, Integer> feedbackID;
    public static volatile CollectionAttribute<Feedback, Feedbackreply> feedbackreplyCollection;
    public static volatile SingularAttribute<Feedback, Users> userID;

}