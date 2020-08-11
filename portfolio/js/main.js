var needPopup = (function() {
    var popup = {};
    popup.html = document.documentElement,
    popup.body = document.body,
    popup, window = window,
    popup.target = 0,
    popup.scrollTopVal = 0,
    popup.scrollHeight = popup.body.scrollHeight > popup.html.scrollHeight ? popup.body.scrollHeight : popup.html.scrollHeight,
    popup.openHtmlClass = popup.scrollHeight > popup.window.innerHeight ? 'needpopup-opened needpopup-scrolled' : 'needpopup-opened';

    return {
        init : function() {
            popup.options = needPopup.config.default;
            
            $(popup.body).on('click','[data-needpopup-show]', function(event) {
				event.preventDefault();
				needPopup.show($(this).data('needpopupShow'),$(this));
			})
        }
    }
});