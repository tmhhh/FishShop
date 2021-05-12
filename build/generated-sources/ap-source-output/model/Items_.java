package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Feedback;
import model.Feedbackreply;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-01-20T10:36:13")
@StaticMetamodel(Items.class)
public class Items_ { 

    public static volatile SingularAttribute<Items, String> itemImageName;
    public static volatile SingularAttribute<Items, String> itemID;
    public static volatile SingularAttribute<Items, String> itemName;
    public static volatile SingularAttribute<Items, Integer> quantity;
    public static volatile CollectionAttribute<Items, Feedback> feedbackCollection;
    public static volatile SingularAttribute<Items, Double> itemPrice;
    public static volatile CollectionAttribute<Items, Feedbackreply> feedbackreplyCollection;
    public static volatile SingularAttribute<Items, String> itemImageData;

}