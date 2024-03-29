<%-- Document : profileuserpage Created on : Jan 15, 2021, 1:32:15 PM Author : pc --%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
              integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous" />
        <link rel="stylesheet" href="CSS/profile.css" />
    </head>

    <body>
        <% Users user = (Users) session.getAttribute("User");
            String message = (String) session.getAttribute("updateMessage");
            if (message == null) {
                message = "";
            }
        %>
        <div class="container">
            <!-- Header -->
            <div class="jumbotron">
                <h1>Your Profile</h1>
            </div>

            <div class="panel-group" id="musicStore">
                <!-- Album and Artist panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <!-- Panel Title -->
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#musicStore" href="#titleAndArtist">
                                Information
                            </a>
                        </h4>
                    </div>

                    <!--Panel Data  enctype="multipart/form-data"-->
                    <form class="form form-horizontal" action="changeProfile" method="post" enctype="multipart/form-data">

                        <div id="titleAndArtist" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row">
                                    <!-- Album Cover image -->
                                    <div class="col-md-3 album-cover">
                                        <div class="col-md-12 thumbnail">
                                            <img class=""
                                                 src="<%= user.getUserAvatar()%>"
                                                 alt="Avatar">
                                            <br>
                                            <div class="text-center">
                                                <!---->
                                                <input type="file" name="file" id="file" class="inputfile" />                                                
                                                <label for="file"><span class="glyphicon glyphicon-pencil"></span> Edit</label>
                                            </div>
                                        </div> 
                                        <h4>ID: <%= user.getUserID()%> </h4>
                                        <a href="#" class="btn btn-default" data-toggle="modal" data-target="#signupWindow">
                                            <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                                            Change password</a>
                                    </div>

                                    <!-- Edit Album Info form -->
                                    <div class="col-md-9">
                                        <!-- Album title -->
                                        <div class="form-group">
                                            <label for="albumTitle" class="col-sm-2 control-label">Your name: </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="userName" class="form-control" id="albumTitle" value="<%= user.getUserName()%>">
                                            </div>
                                        </div>
                                        <!-- Artist -->
                                        <div class="form-group">
                                            <label for="artist" class="col-sm-2 control-label">Your phone: </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="userPhone" class="form-control" id="artist" value="<%= user.getUserPhone()%>">
                                            </div>
                                        </div>
                                        <!-- Record studio -->
                                        <div class="form-group">
                                            <label for="studioInfo" class="control-label col-sm-2">Your email: </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="userEmail" id="studioInfo" class="form-control" value="<%=user.getUserEmail()%>">
                                            </div>
                                        </div>

                                        <!-- Album desciption -->
                                        <div class="form-group">
                                            <label for="desc" class="col-sm-2 control-label">Your address: </label>
                                            <div class="col-sm-10">
                                                <textarea name="userAddress" id="desc" rows="5" class="form-control"value="<%= user.getUserAddress()%>" ></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <input type="submit" class="btn btn-success col-sm-4 col-sm-offset-4" data-toggle="modal"
                                                   data-target="#submitWindow" value="Change">
                                        </div>
                                    </div>
                                    <!-- End album info -->
                                </div>
                            </div>
                        </div>
                    </form>

                    <!-- Expert rewievs-->
                    <!--                    <div class="panel panel-default">
                                            <div class="panel-heading">
                                                 Title 
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#musicStore" href="#expertReview">
                                                        Expert Review
                                                    </a>
                                                </h4>
                                            </div>
                                             Panel data 
                                            <div id="expertReview" class="panel-collapse collapse">
                                                <div class="panel-body">
                    
                                                     Reviews 
                                                    <div class="form-group">
                                                        <label for="reviews" class="col-sm-1 control-label">Reviews:</label>
                                                        <div class="col-sm-11">
                                                            <textarea name="name" id="reviews" rows="6" class="form-control"></textarea>
                                                        </div>
                                                    </div>
                                                     Rating 
                                                    <div class="form-group">
                                                        <label for="rating" class="col-sm-1">Rate album:</label>
                                                        <div class="btn-group col-sm-11" id="rating" data-toggle="buttons">
                                                            <label class="btn btn-warning">
                                                                <input type="radio" name="options" id="star1" autocomplete="off" checked>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </label>
                                                            <label class="btn btn-warning">
                                                                <input type="radio" name="options" id="star2" autocomplete="off">
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </label>
                                                            <label class="btn btn-warning">
                                                                <input type="radio" name="options" id="star3" autocomplete="off">
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </label>
                                                            <label class="btn btn-warning">
                                                                <input type="radio" name="options" id="star4" autocomplete="off">
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </label>
                                                            <label class="btn btn-warning">
                                                                <input type="radio" name="options" id="star5" autocomplete="off">
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                                <span class="glyphicon glyphicon-star"></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                     End rating 
                                                </div>
                                            </div>
                                        </div>-->
                </div>
                <!--  End accordion-->

                <!-- Submit button -->
                <br>


                <!-- MODAL WINDOWS -->
                <!-- Login window -->
                <!-- Signup window -->
                <div class="modal fade" id="signupWindow" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h3 class="modal-title text-center">Sign up</h4>
                            </div>
                            <!-- Change password tab -->
                            <div class="modal-body">
                                <form class="form-horizontal" action="changePassword" method="post">
                                    <div class="form-group row">
                                        <label for="userLogin" class="control-label col-sm-2">Login:</label>
                                        <div class="input-group col-sm-9" id="userLogin">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-user"></span>
                                            </span>
                                            <input type="text" class="form-control" name="userLogin">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="userPassword" class="control-label col-sm-2">Password:</label>
                                        <div class="input-group col-sm-9" id="userPassword">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-eye-close"></span>
                                            </span>
                                            <input type="password" name="password" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="repeatPassword" class="control-label col-sm-2">Repeat password:</label>
                                        <div class="input-group col-sm-9" id="repeatPassword">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-eye-close"></span>
                                            </span>
                                            <input type="password" name="repeatPassword" class="form-control">
                                        </div>
                                    </div>
                                    <!--                      <div class="form-group row">
                                                            <label class="checkbox-inline col-sm-10 col-sm-offset-2">
                                                              <input type="checkbox" value="">I agree with
                                                              <a href="#">site policy</a>.
                                                            </label>
                                                          </div>-->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-success">Submit</button>
                                    </div>
                                </form>
                            </div>
                            <!--  Modal footer-->

                        </div>
                    </div>

                </div>
                <p><%=message%></p>
                <!--  Submit confimation -->
                <!--                <div class="modal fade" id="submitWindow" tabindex="-1" role="dialog" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                             Modal Header 
                                            <div class="modal-header">
                                                <h3 class="modal-title text-center">Are you sure?</h4>
                                            </div>
                                             Modal Body 
                                            <div class="modal-body">
                                                Is everything is correct? You can't edit information after submitting.
                                            </div>
                                              Modal footer
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
                                                <button type="button" class="btn btn-primary">Submit</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>-->

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                <script src="JS/userpage.js"></script>
                </body>

                </html>