document.getElementById("Section Name0").style.backgroundColor = "#ddd";

function countRate(r_id,star)
{
	
	var labelid=document.getElementById(r_id.id);
	document.getElementById(labelid.id).innerHTML=star;
	
	for(var i=1;i<=star;i++)
		{	var b_id="b"+i+labelid.id;
			document.getElementById(b_id).innerHTML ='<img src="star.png" />'; 
		
		}
	for(var i=5;i>star;i--)
	{	var b_id="b"+i+labelid.id;
		document.getElementById(b_id).innerHTML ='<img src="starUnfilled.png" />'; 
	
	}
	
}
function loadForm(section)
{
	var s="formsection"+section;	
	var elements = document.getElementsByClassName("formsection");
	
    for(var i = 0, length = elements.length; i < length; i++) {
      if(elements[i].id==s)
          elements[i].style.display = 'block';
      else
    	  elements[i].style.display = 'none';
      
    }
    var elements1 = document.getElementsByClassName("tablinks");
      var num=Number(section);
      alert(num);
      for(var i = 0, length = elements1.length; i < length; i++) {
    	
          if(elements1[i].id=="Section Name"+num)
              elements1[i].style.backgroundColor = "#ddd";
          else
        	  elements1[i].style.backgroundColor = "#f1f1f1";
          }	
      document.getElementById("indicator").style.display='none';
}
function loadCriteria(c1,c2,c3)
{
	document.getElementById("l1").innerHTML =c1;
	document.getElementById("l2").innerHTML =c2;
	document.getElementById("l3").innerHTML =c3;
	document.getElementById("indicator").style.display='block';
}
window.history.forward();
function noBack()
{
    window.history.forward();
}