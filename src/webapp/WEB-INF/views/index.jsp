<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<form id="fruitForm">
     	<label for="input">Choose a option:</label>
        <select id="values" name="values">
            <option value="create">Create Table</option>
            <option value="insert">Insert data</option>
            <option value="upload">Bulk Upload</option>
            <option value="show">Show Data</option>
        </select>
        
        
        <button type = "button" value = "Button" id="upload1">Upload</button>
        <button type="submit" onclick="submitForm()">Submit</button>
     </form>
     
     
     
     
      <script>
		
      $(document).ready(function() {

    	  console.log("hiii");
    	  var btn1 = document.getElementById("upload1");
    	  btn1.hide();
    	  
          function submitForm() {
              var temp = document.getElementById("values").value;
              //alert("You selected: " + selectedFruit);
              // Here you can perform any further actions, such as submitting the form via AJAX
              
              if(temp=="create"){
              	temp = "createTable";
              }
              else if(temp=="insert"){
              	temp= "insertData";
              }
              else if(temp =="upload"){
              	temp = "upload";
              	btn1.show();
              }
              else{
              	temp = "show";
              }
              
              
              
              return false; // Prevent form submission
          }

    	});
    </script>
</body>
</html>
