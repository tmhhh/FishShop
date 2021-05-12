<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="SignupForm/css/style.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous" />
        <link rel="stylesheet" href="SignupForm/css/logo.css" />
        <link rel="stylesheet" href="SignupForm/css/signUp.css" />

        <title>Sign Up</title>
    </head>

    <body>
        <% String message = (String) session.getAttribute("Message");
            if (message == null) {
                message = " ";
            }
        %>
        <form action="Register" class="form" id="form-signUp" method="POST">
            <div class="logo">
                <div class="image-Logo"></div>
                <div class="brand">
                    <h1>7COLORS</h1>
                    <h1>7COLORS</h1>
                </div>
            </div>
            <h1 class="heading">SIGN UP</h1>
            <h2 class="des">Create a new account</h2>
            <div class="spacer"></div>
            <div class="container row">
                <div class="col">
                    <div class="form-group">
                        <label for="fullname" class="lb-control">Fullname</label>
                        <input type="text" name="fullname" id="fullname" placeholder="Input your name" class="form-input"
                               rules="isRequired" />
                        <span class="error-control"></span>
                    </div>
                    <div class="form-group">
                        <label for="ID" class="lb-control">ID
                            <span style="color: red" class="error-control"><%=message%></span>

                        </label>

                        <input type="text" name="id" id="fullname" placeholder="Input your ID" class="form-input"
                               rules="isRequired" />
                    </div>

                    <div class="form-group">
                        <label for="password" class="lb-control">Password</label>
                        <input type="password" name="password" id="password" placeholder="Input your word" class="form-input"
                               rules="isRequired|min:6" />
                        <span class="error-control"></span>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password" class="lb-control">Confirm Password</label>
                        <input type="password" name="confirm-password" id="confirm-password" placeholder="Confirm your password"
                               class="form-input" rules="isRequired|isConfirm:password" />
                        <span class="error-control"></span>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="address" class="lb-control">Address</label>
                        <input type="text" name="address" id="address" placeholder="Input your address" class="form-input"
                               rules="isRequired" />
                        <span class="error-control"></span>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="lb-control">Phone number</label>
                        <input type="text" name="phone" id="phone" placeholder="Input your phone number" class="form-input"
                               rules="isRequired" />
                        <span class="error-control"></span>
                    </div>
                    <div class="form-group">
                        <label for="email" class="lb-control">Email</label>
                        <input type="text" name="email" id="email" placeholder="Input your email" class="form-input"
                               rules="isRequired" />
                        <span class="error-control"></span>
                    </div>
                </div>
            </div>


            <button type="submit" class="btn-submit">SUBMIT</button>
        </form>

        <script src="SignupForm/js/Main.js"></script>
        <script>
            new Validator("#form-signUp");
        </script>
    </body>

</html>