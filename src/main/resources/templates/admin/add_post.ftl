<!DOCTYPE html>
<html>
<title>Pizza</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/w3.css">
<script src="/js/w3.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Amatic+SC">
<style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Amatic SC", sans-serif}
</style>
<body>
<!-- Start Content -->
<div id="home" class="w3-content">

    <!-- Navbar (Sits on top) -->
<#--    <div class="w3-top w3-bar w3-xlarge w3-black w3-opacity-min">-->
<#--        <a href="#home" class="w3-bar-item w3-button">HOME</a>-->
<#--        <a href="#menu" class="w3-bar-item w3-button">MENU</a>-->
<#--        <a href="#about" class="w3-bar-item w3-button">ABOUT</a>-->
<#--        <a href="#contact" class="w3-bar-item w3-button">CONTACT</a>-->
<#--    </div>-->

    <!-- Header image -->
    <div class="w3-display-container w3-grayscale-min">
        <form action="/admin/blogposts" method="POST">
            <div class="row">
                <div class="col-md-9">
                    <label>Title</label>
                    <div>
                        <input type="text" class="form-control" style="width:100%" maxlength="128"
                               field="*{title}" placeholder="Enter title here"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <input type="submit" value="Save"/>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
</html>