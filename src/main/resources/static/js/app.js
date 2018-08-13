// Add document ready function to prevent incorrect performance on website (i.e., first load website, then perform js/ jQuery code)
$(document).ready(function (){

    // Add border to article images
    $('img.featurette-image.img-fluid.mx-auto').hover(
        function () {
            var $this = $(this);
            $this.css('border', '5px double black');
        },
        function () {
            var $this = $(this);
            $this.css('border', 'none');
        }
    );

    // Subscribe (Create an account with the website)
    $('#subscribeForm').submit(function (e){
        e.preventDefault();

        /*var user = $('#subscribersUsername').val();
        var email = $('#subscribersEmail').val();
        var password = $('#subscribersPassword').val();
        console.log(user + ", " + email + ", " + password);
        var url = $(this).attr('action');
        console.log(url);

        $.post(url, {user:user, email:email, password:password}).done(function (data){
            console.log('You successfully subscribed!');
            console.log(data);
        });*/
        var $user = $('#subscribersUsername');
        var $email = $('#subscribersEmail');
        var $password = $('#subscribersPassword');

        var post = {
            username: $user.val(),
            email: $email.val(),
            password: $password.val(),
        };

        console.log(post.username + ", " + post.email + ", " + post.password);
        $.ajax({
            method: 'POST',
            url: '/users.json',
            data: post,
            success: function(newPost) {
                alert("You successfully subscribed as: " + "username: " + newPost.username + ", email: " + newPost.email + ", " + newPost.password);
            },
            error: function() {
                alert("Error on saving post");
            }
        });
    });

    // Sign in your account
    $('#signInForm').submit(function (e){
        e.preventDefault();

        var email = $('#signInEmail').val();
        var password = $('#signInPassword').val();
        var url = $(this).attr('action');

        $.ajax({
            method: 'GET',
            url: '/users.json',
            dataType: 'json'
        }).done(function(data){        
            /*console.log("Email entered: " + email + "; password " + password);
            console.log(data);*/
            var isFound = false;
            var name;
            $.each(data, function(index, user){
                if (user.email === email && user.password === password) {
                    name = user.username;
                    isFound = true;
                    return;
                }
            });

            if(isFound === true) {
                alert("You successfully signed in as "+ name);
            } else {
                alert("Wrong email or password!");
            };
        });
    });

});
