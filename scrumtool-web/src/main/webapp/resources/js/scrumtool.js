$(function(){
	
	dragula([
		document.getElementById('story_column_1'),
		document.getElementById('story_column_2'),
		document.getElementById('story_column_3')
	])
		.on('drop', function(elem, target){
			changeStoryCategory($(elem).data('storyid'), $(target).data('storycolumnid'));
		});
	
	$("#toggleBtn").click(function() {
		$(".addForm").slideToggle();
		$("#toggleBtn span").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
		});
	
	$(".btnSup").click(function(){
		var id = $(this).attr('id');
		var $that=$(this).parent();

		$.ajax({
			url : "story/"+id,
			type : "DELETE",
			success : function(){
				$that.remove();
			}
		})
		
	});
	
	
	
});

