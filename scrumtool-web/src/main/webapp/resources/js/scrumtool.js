$(function(){
	
	dragula([
		document.getElementById('story_column_1'),
		document.getElementById('story_column_2'),
		document.getElementById('story_column_3')
	])
		.on('drop', function(elem, target){
			console.log($(elem).data('storyid'));
			console.log($(target).data('storycolumnid'));
		});
	
	
	
	
	
});

