<!DOCTYPE html>
<html>
<title>Construct 2018 </title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="resources/img/icons/favicon.ico"/>


<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" type="text/css" href="resources/css/myStyle.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
.w3-quarter img{margin-bottom: -6px; cursor: pointer}
.w3-quarter img:hover{opacity: 0.6; transition: 0.3s}
</style>
<body class="w3-light-grey">
    
<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if(userName== null) response.sendRedirect("login.jsp");
%>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-black w3-animate-right w3-top w3-text-light-grey w3-large" style="z-index:3;width:250px;font-weight:bold;display:none;right:0;" id="mySidebar">
  <a href="javascript:void()" onclick="w3_close()" class="w3-bar-item w3-button w3-center w3-padding-32">CLOSE</a> 
  <a href="Navigate?ref=home" onclick="w3_close()" class="w3-bar-item w3-button w3-center w3-padding-16">Home</a> 
  <a href="profile?id=<c:out value='${sessionScope.userid}'/>" onclick="w3_close()" class="w3-bar-item w3-button w3-center w3-padding-16">My Profile</a> 
  <a href="logout" onclick="w3_close()" class="w3-bar-item w3-button w3-center w3-padding-16">Logout</a> 
  <a href="about.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-center w3-padding-16">About Construction 2018</a>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-white w3-xlarge w3-padding-16">
  <span class="w3-left w3-padding">Construction 2018
      <bean:define id="username" name="username" scope="session" />
        <bean:write name="username" />
  </span>
  <a href="javascript:void(0)" class="w3-right w3-button w3-white" onclick="w3_open()">Hi! <c:out value='${sessionScope.username}'/><span class="gear"></span></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content" style="max-width:1600px;margin-top:83px">
  
  <div>
    <img src="resources/img/background.jpg" class="bg-image background">
    <div class="background"></div>
  </div>

    <div class="center-block w3-white">
        
        <form id="regForm" action="createpost" enctype="multipart/form-data" method="post">

            <h1>Create a Posting:</h1>

<!-- One "tab" for each step in the form: -->
<div class="tab">Step 1 - Name Your posting :
    <p><input type="text" placeholder="Title..." name="title" oninput="this.className = ''"></p>
    <p><input type="text" placeholder="Short description..." name="desc" oninput="this.className = ''"></p>
</div>

<div class="tab">Step 2 - Explain your concerns in detail :
    <p><textarea name="explain" rows="4" cols="50" oninput="this.className = ''">

</textarea></p>
</div>

<div class="tab">Step 3 - Upload Pictures :
  <p>Picture 1:<input type="file" name="file1" id="file1" oninput="this.className = ''"/></p>
  <p>Picture 2:<input type="file" name="file2" id="file2" oninput="this.className = ''"/></p>
  <p>Picture 3:<input type="file" name="file3" id="file3" oninput="this.className = ''"/></p>
</div>

<div class="tab">Step 4 - Choose a category :
  <p>
      <select name="cate" oninput="this.className = ''">
  <option value="1">Problem</option>
  <option value="2">Suggestion</option>
  <option value="3">Homes</option>
  <option value="4">Purchases</option>
</select>
  </p>
</div>

<div style="overflow:auto;">
  <div style="float:right;">
    <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
    <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
  </div>
</div>

<!-- Circles which indicates the steps of the form: -->
<div style="text-align:center;margin-top:40px;">
  <span class="step"></span>
  <span class="step"></span>
  <span class="step"></span>
  <span class="step"></span>
</div>

</form>
    </div>

</div>
  
<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}

function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
}

var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
  // This function will display the specified tab of the form ...
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";
  // ... and fix the Previous/Next buttons:
  if (n == 0) {
    document.getElementById("prevBtn").style.display = "none";
  } else {
    document.getElementById("prevBtn").style.display = "inline";
  }
  if (n == (x.length - 1)) {
    document.getElementById("nextBtn").innerHTML = "Submit";
  } else {
    document.getElementById("nextBtn").innerHTML = "Next";
  }
  // ... and run a function that displays the correct step indicator:
  fixStepIndicator(n)
}

function nextPrev(n) {
  // This function will figure out which tab to display
  var x = document.getElementsByClassName("tab");
  // Exit the function if any field in the current tab is invalid:
  if (n == 1 && !validateForm()) return false;
  // Hide the current tab:
  x[currentTab].style.display = "none";
  // Increase or decrease the current tab by 1:
  currentTab = currentTab + n;
  // if you have reached the end of the form... :
  if (currentTab >= x.length) {
    //...the form gets submitted:
    document.getElementById("regForm").submit();
    return false;
  }
  // Otherwise, display the correct tab:
  showTab(currentTab);
}

function validateForm() {
  // This function deals with validation of the form fields
  var x, y, i, valid = true;
  x = document.getElementsByClassName("tab");
  y = x[currentTab].getElementsByTagName("input");
  // A loop that checks every input field in the current tab:
  for (i = 0; i < y.length; i++) {
    // If a field is empty...
    if (y[i].value == "") {
      // add an "invalid" class to the field:
      y[i].className += " invalid";
      // and set the current valid status to false:
      valid = false;
    }
  }
  // If the valid status is true, mark the step as finished and valid:
  if (valid) {
    document.getElementsByClassName("step")[currentTab].className += " finish";
  }
  return valid; // return the valid status
}

function fixStepIndicator(n) {
  // This function removes the "active" class of all steps...
  var i, x = document.getElementsByClassName("step");
  for (i = 0; i < x.length; i++) {
    x[i].className = x[i].className.replace(" active", "");
  }
  //... and adds the "active" class to the current step:
  x[n].className += " active";
}


</script>


</body>
</html>