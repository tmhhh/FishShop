<%--
    Document   : adminpage
    Created on : Jan 14, 2021, 1:29:23 PM
    Author     : pc
--%>

<%@page import="dao.orderedItemsDB"%>
<%@page import="model.Itemsorder"%>
<%@page import="model.cartItem"%>
<%@page import="model.Cart"%>
<%@page import="dao.adminPageDB"%>
<%@page import="java.util.List"%>
<%@page import="model.Items"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/adminpage.css">
        <link rel="stylesheet" href="CSS/logo.css"/>
    </head>

    <body>
        <div class="logoo">
            <a  href="#">
                <div class="logo ">
                    <div class="image-Logo"></div>
                    <div class="brand">
                        <h1>7COLORS</h1>
                        <h1>7COLORS</h1>
                    </div>
                </div>
            </a>
        </div>

        <div class="adcontainer">

            <div class="adleft">
                <a href="#" id="uploadfunc" style="text-decoration: none;"  onclick="apUploadform()">Upload</a>
                <a href="#" id="managefunc" style="text-decoration: none;" onclick="apManageform()">Storage</a>
                <a href="#" id="orderfunc" style="text-decoration: none;" onclick="apOrderform()">Order</a>
                <a href="#" id="blogfunc" style="text-decoration: none;" onclick="apBlogform()">Blog</a>
                <div class="footerright">
                    <a style="color: orange;text-decoration: none;" href="homepage.jsp"></a>
                    <p style="color: rgb(53, 38, 17);"> © 2020 Copyright: HTHK.com</p>
                </div>
            </div>

            <div id="uploading" class="uploadin">
                <h2 class="myclass">UPLOADING FISH</h2>
                <form action="upload" method="post" enctype="multipart/form-data">
                    <tr class="form-group">
                    <label>ID</label>
                    <input type="text" class="form-control" name="itemId" placeholder="Enter id">
                    </tr>
                    <div class="form-group">
                        <br>
                        <label>Fish Name</label>
                        <input type="text" class="form-control" name="itemName" placeholder="Enter name">
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="text" class="form-control" name="itemPrice" placeholder="Enter price">
                    </div>
                    <div class="form-group">
                        <label>Quantity</label>
                        <input type="text" class="form-control" name="itemQuantity" placeholder="Enter quantity">
                    </div>
                    <div class="form-group">
                        <label>Photo</label> <br />
                        <input type="file" class="form-control" name="itemPhoto" placeholder="Enter photo">
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="reset" class="btn btn-primary">Cancel</button>
                </form>
            </div>

            <% List<Items> listItems = adminPageDB.getAllItems();%>
            <div id="managing" class="managin">
                <h2>LIST INFORMATION OF FISH</h2>
                <div id="showform" class="showform">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>ID</th>
                                <th>Fish Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Items item : listItems) {%>
                            <tr>
                                <td width="100"><img src="<%= item.getItemImageData()%>" alt="Image" width="100" height="100" /></td>
                                <td width="70"> <%= item.getItemID()%></td>
                                <td width="200"><%= item.getItemName()%></td>
                                <td >$<%= item.getItemPrice()%></td>
                                <td width="70"><%= item.getQuantity()%></td>
                                <td width="150" >
                                    <a class="btn btn-primary btn-sm"  href="edit.jsp?code=<%= item.getItemID()%>">Edit</a>
                                    <form action="delete" method="post">
                                        <input type="hidden" name="code" value="<%= item.getItemID()%>">
                                        <input  type="submit" class="btn btn-danger btn-sm" value="Delete">
                                    </form>
                                </td>
                            </tr>
                            <%}%>

                        </tbody>
                    </table>
                </div>
            </div>

            <div id="ordering" class="orderin">
                <h2>LIST ORDER</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>ID</th>
                            <th>Fish Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Address</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%List<Itemsorder> listOrder = orderedItemsDB.getListOrder();
                        for(Itemsorder order :listOrder)
                        {
                                                            
                        %>

                        <tr>

                            <td><%= order.getUsers().getUserName() %></td>
                            <td><%= order.getItemsorderPK().getOrderID() %></td>
                            <td><%= order.getItems().getItemName() %></td>
                            <td><%= order.getQuantity() %></td>
                            <td><%= order.getQuantity() * order.getItems().getItemPrice() %></td>
                            <td><%= order.getUsers().getUserAddress() %></td>
                            <%  } %>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="blog" class="blog">
                <form action="uploadBlog">
                    <div class="form-group">
                        <br>
                        <label>BlogID</label>
                        <input type="text" class="form-control" name="blogid" placeholder="Enter id">
                    </div>
                    <div class="form-group">
                        <br>
                        <label>Title</label>
                        <input type="text" class="form-control" name="blogtitle" placeholder="Enter title">
                    </div>
                    <div class="form-group">
                        <label>Content</label>
                        <textarea class="form-control" name="blogcontent" id="" cols="25" rows="30" placeholder="Enter blog content"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" name="blogdesc" id="" cols="5" rows="5" placeholder="Enter description"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Photo</label> <br />
                        <input type="file" class="form-control" name="blogphoto" placeholder="Enter photo">
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
        <script src="JS/handleform.js"></script>
    </body>

</html>

