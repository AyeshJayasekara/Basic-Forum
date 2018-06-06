<!DOCTYPE html>
<html>
<title>Construct 2018 </title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="resources/img/icons/favicon.ico"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
        <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" type="text/css" href="resources/css/myStyle.css">

<style>
body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
.w3-quarter img{margin-bottom: -6px; cursor: pointer}
.w3-quarter img:hover{opacity: 0.6; transition: 0.3s}
</style>
<body class="w3-light-grey">
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
  <span class="w3-left w3-padding">Construct 2018</span>
  <a href="javascript:void(0)" class="w3-right w3-button w3-white" onclick="w3_open()">Hi! <c:out value='${sessionScope.username}'/><span class="gear"></span></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main w3-content" style="max-width:1600px;margin-top:83px">
  
  <div>
    <img src="images/background.jpg" class="bg-image background">
    <div class="background"></div>
  </div>

  <div class=" view-container">
      <div class="view-port">
        <!-- *****post***** -->
     <c:forEach var="p" items="${post}">
         <div class="post">
          <h2><c:out value="${p.title}"/></h2>
          <hr>
          <p> 
            <c:out value="${p.descri}"/>
          </p>
          <div style="display: flex;height:auto;">
              <form method="get" action="show">
                  <input type="hidden" name="id" value="<c:out value="${p.id}"/>">
                  <input type="submit" Value="View Post">
              </form>
          </div>
          <br>
        </div>
          <br>
     </c:forEach>
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

</script>


</body>
</html>









































