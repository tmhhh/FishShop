package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Feedback;
import model.Items;
import model.Users;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-01-20T10:36:13")
@StaticMetamodel(Feedbackreply.class)
public class Feedbackreply_ { 

    public static volatile SingularAttribute<Feedbackreply, Items> itemID;
    public static volatile SingularAttribute<Feedbackreply, Date> dateCreated;
    public static volatile SingularAttribute<Feedbackreply, Users> userReplyID;
    public static volatile SingularAttribute<Feedbackreply, Feedback> feedbackID;
    public static volatile SingularAttribute<Feedbackreply, Integer> feedbackReplyID;
    public static volatile SingularAttribute<Feedbackreply, String> feedbackReplyIDContent;

}