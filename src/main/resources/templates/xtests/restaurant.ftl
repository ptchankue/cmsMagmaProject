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
    <div class="w3-top w3-bar w3-xlarge w3-black w3-opacity-min">
        <a href="#home" class="w3-bar-item w3-button">HOME</a>
        <a href="#menu" class="w3-bar-item w3-button">MENU</a>
        <a href="#about" class="w3-bar-item w3-button">ABOUT</a>
        <a href="#contact" class="w3-bar-item w3-button">CONTACT</a>
    </div>

    <!-- Header image -->
    <div style="height:800px;background-image:url('/img/img_pizza.jpg');background-size:cover" class="w3-display-container w3-grayscale-min">
        <div class="w3-display-bottomleft">
            <span class="w3-tag w3-xlarge">Open from 10am to 12pm</span>
        </div>
        <div class="w3-display-middle w3-center">
            <span class="w3-text-white" style="font-size:100px">thin<br>CRUST PIZZA</span>
            <p><a href="#menu" class="w3-button w3-xxlarge w3-black">Let me see the menu</a></p>
        </div>
    </div>

    <!-- Menu -->
    <div id="menu" class="w3-container w3-black w3-xxlarge w3-padding-64">
        <h1 class="w3-center w3-jumbo w3-padding-32">THE MENU</h1>
        <div class="w3-row w3-center w3-border w3-border-dark-grey">
            <a href="#pizza"><div class="w3-third w3-padding-large w3-red">Pizza</div></a>
            <a href="#pasta"><div class="w3-third w3-padding-large w3-hover-red">Pasta</div></a>
            <a href="#starters"><div class="w3-third w3-padding-large w3-hover-red">Starters</div></a>
        </div>
        <div id="pizza" class="w3-container w3-white w3-padding-32">
            <h1><b>Margherita</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$12.50</span></h1>
            <p class="w3-text-grey">Fresh tomatoes, fresh mozzarella, fresh basil</p>
            <hr>
            <h1><b>Formaggio</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$15.50</span></h1>
            <p class="w3-text-grey">Four cheeses (mozzarella, parmesan, pecorino, jarlsberg)</p>
            <hr>
            <h1><b>Chicken</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$17.00</span></h1>
            <p class="w3-text-grey">Fresh tomatoes, mozzarella, chicken, onions</p>
            <hr>
            <h1><b>Pineapple'o'clock</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$16.50</span></h1>
            <p class="w3-text-grey">Fresh tomatoes, mozzarella, fresh pineapple, bacon, fresh basil</p>
            <hr>
            <h1><b>Meat Town</b> <span class="w3-tag w3-red w3-round">Hot!</span>
                <span class="w3-right w3-tag w3-dark-grey w3-round">$20.00</span></h1>
            <p class="w3-text-grey">Fresh tomatoes, mozzarella, hot pepporoni, hot sausage, beef, chicken</p>
        </div>
        <h1 id="pasta" class="w3-center w3-jumbo w3-padding-32">PASTA</h1>
        <div class="w3-container w3-white w3-padding-32">
            <h1><b>Lasagna</b> <span class="w3-tag w3-grey w3-round">Popular</span>
                <span class="w3-right w3-tag w3-dark-grey w3-round">$13.50</span></h1>
            <p class="w3-text-grey">Special sauce, mozzarella, parmesan, ground beef</p>
            <hr>
            <h1><b>Ravioli</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$14.50</span></h1>
            <p class="w3-text-grey">Ravioli filled with cheese</p>
            <hr>
            <h1><b>Spaghetti Classica</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$11.00</span></h1>
            <p class="w3-text-grey">Fresh tomatoes, onions, ground beef</p>
        </div>
        <h1 id="starters" class="w3-center w3-jumbo w3-padding-32">STARTERS</h1>
        <div class="w3-container w3-white w3-padding-32">
            <h1><b>Today's Soup</b> <span class="w3-tag w3-grey w3-round">Seasonal</span>
                <span class="w3-right w3-tag w3-dark-grey w3-round">$5.50</span></h1>
            <p class="w3-text-grey">Ask the waiter</p>
            <hr>
            <h1><b>Bruschetta</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$8.50</span></h1>
            <p class="w3-text-grey">Bread with pesto, tomatoes, onion, garlic</p>
            <hr>
            <h1><b>Garlic bread</b> <span class="w3-right w3-tag w3-dark-grey w3-round">$9.50</span></h1>
            <p class="w3-text-grey">Grilled ciabatta, garlic butter, onions</p>
        </div>
    </div>

    <!-- End Content -->
</div>
</body>
</html>