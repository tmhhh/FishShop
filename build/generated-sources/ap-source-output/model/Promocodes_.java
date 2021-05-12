package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-01-20T10:36:13")
@StaticMetamodel(Promocodes.class)
public class Promocodes_ { 

    public static volatile SingularAttribute<Promocodes, String> codeID;
    public static volatile SingularAttribute<Promocodes, String> code;
    public static volatile SingularAttribute<Promocodes, Date> codeDate;
    public static volatile SingularAttribute<Promocodes, Double> codeValue;

}