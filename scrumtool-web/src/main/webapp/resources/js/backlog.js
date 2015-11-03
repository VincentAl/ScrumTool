dragula([ document.getElementById('1'), document.getElementById('2'),
		document.getElementById('3') ])

$(document).ready(function() {
	$("#toggleBtn").click(function() {
		$(".addForm").slideToggle();
		$("#toggleBtn span").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
		});
	
	$(".btnSup").click(function(){
		var id = $(this).attr('id');
		var $that=$(this).parent();
		
		$.ajax({
			url : "story/"+id,
			type : "GET",
			
			success : function(){
				
				$that.remove();
			}
		})
		
	});
});