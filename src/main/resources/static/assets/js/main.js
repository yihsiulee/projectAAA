/*
	Retrospect by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
*/

(function($) {

	skel
		.breakpoints({
			xlarge: '(max-width: 1680px)',
			large: '(max-width: 1280px)',
			medium: '(max-width: 980px)',
			small: '(max-width: 736px)',
			xsmall: '(max-width: 480px)'
		});

	$(function() {

		var	$window = $(window),
			$body = $('body');

		// Disable animations/transitions until the page has loaded.
			$body.addClass('is-loading');

			$window.on('load', function() {
				window.setTimeout(function() {
					$body.removeClass('is-loading');
				}, 100);
			});

		// Fix: Placeholder polyfill.
			$('form').placeholder();

		// Prioritize "important" elements on medium.
			skel.on('+medium -medium', function() {
				$.prioritize(
					'.important\\28 medium\\29',
					skel.breakpoint('medium').active
				);
			});

		// Nav.
			$('#nav')
				.append('<a href="#nav" class="close"></a>')
				.appendTo($body)
				.panel({
					delay: 500,
					hideOnClick: true,
					hideOnSwipe: true,
					resetScroll: true,
					resetForms: true,
					side: 'right'
				});


		// // qa.html
		// $("p.Question")
		// 	.css({cursor:"pointer"})
		// 	.click(function(){
		// 		$(this).next().toggle("normal");
		// 	});
		// // activityCheckTable.html
		// var acc = document.getElementsByClassName("accordion");
		// var i;
		//
		// for (i = 0; i < acc.length; i++) {
		// 	acc[i].addEventListener("click", function() {
		// 		this.classList.toggle("active");
		// 		var panel = this.nextElementSibling;
		// 		if (panel.style.maxHeight){
		// 			panel.style.maxHeight = null;
		// 		} else {
		// 			panel.style.maxHeight = panel.scrollHeight + "px";
		// 		}
		// 	});
		// }
		// // activityHold.html articlePost.html 檔案上傳
		// $('#file-uploads').change(function() {
		// 	var i = $(this).prev('label').clone();
		// 	var file = $('#file-uploads')[0].files[0].name;
		// 	$(this).prev('label').text(file);
		// });



		// activityHold.html participant

		// function updateParticipantNumber(value){
		// 	document.getElementById("participantNumber").value = value*3;
		// }





	});
})(jQuery);

