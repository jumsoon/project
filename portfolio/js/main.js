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
            });
            
            $(popup.body).on('click','.needpopup_wrapper .remove, .needpopup_remover', function(event) {
				event.preventDefault();
				needPopup.hide();
            });
            
            $(popup.body).on('click','.needpopup_wrapper', function(event) {
				console.log($(event.target).is('.needpopup_wrapper'));
				if (!$(event.target).is('.needpopup_wrapper')) return;

				event.preventDefault();
                if (!popup.options.closeOnOutside) return;

                if ($(event.target).closest('.needpopup').length
                || $(event.target).is('.needpopup, .remove, .needpopup_remover')) return;

				needPopup.hide();
            });
            
            $(document).keydown(function(event){
				if (event.which == 27) {
					needPopup.hide();
				}
            })
            
            popup.resizeTimeout = 0;
			popup.resizeAllowed = true;
			$(popup.window).on('resize',function() {
                clearTimeout(popup.resizeTimeout);
				if (popup.resizeAllowed) {
                    popup.resizeAllowed = false;
                    needPopup.centrify();
                    popup.scrollHeight = 
                    popup.body.scrollHeight > popup.html.scrollHeight ? 
                    popup.body.scrollHeight : popup.html.scrollHeight,
                    popup.openHtmlClass = 
                    popup.scrollHeight > popup.window.innerHeight ? 
                    'needpopup-opened needpopup-scrolled' : 'needpopup-opened';
                }
                popup.resizeTimeout = setTimeout(function() {
                    popup.resizeAllowed = true;
                  }, 100);
            });

            popup.wrapper = document.createElement('div');
			popup.wrapper.className = 'needpopup_wrapper';
			popup.body.appendChild(popup.wrapper);
			popup.wrapper = $(popup.wrapper);
        },

        show : function(_target, _trigger) {
            if (!_trigger) {
				popup.trigger = 0;
            }
			else {
                popup.trigger = _trigger;
            }

            if (popup.target)
				needPopup.hide(true);
			else {
				popup.scrollTopVal = popup.window.pageYOffset;

				$(popup.body).css({'top': -popup.scrollTopVal});
				$(popup.html).addClass(popup.openHtmlClass);
            }
            
            popup.target = $(_target);

            popup.options = needPopup.config['default'];
			if (!!popup.target.data('needpopupOptions'))
                $.extend( popup.options, needPopup.config[popup.target.data('needpopupOptions')] );
                
            popup.minWidth = popup.target.outerWidth();
            
            popup.wrapper.append(popup.target);
			if (popup.options.removerPlace == 'outside')
				popup.wrapper.after('<a href="#" class="needpopup_remover"></a>');
			else if (popup.options.removerPlace == 'inside')
                popup.target.append('<a href="#" class="needpopup_remover"></a>'); 
                
            popup.options.onBeforeShow.call(popup,popup.target);
            
			popup.target.show();
			needPopup.centrify();

            setTimeout(function(){
				popup.target.addClass('opened');
				popup.options.onShow.call(popup,popup.target);
			},10);
        },
        
        hide : function(_partial) {
            popup.target.hide().removeClass('opened');
            $('.needpopup_remover').remove();
            if (!_partial) {

				// unblock page scroll
				$(popup.html).removeClass(popup.openHtmlClass).removeClass('needpopup-overflow');
				$(popup.body).css({'top': 0}).scrollTop(popup.scrollTopVal);
				$(popup.html).scrollTop(popup.scrollTopVal);
            }
            popup.options.onHide.call(popup,popup.target);
            popup.target = 0;
        },
        
        centrify : function() {
            if (popup.target) {
                if (popup.target.outerHeight() > popup.window.innerHeight) {
                    popup.target.addClass('stacked');
                }
                else {
					popup.target.removeClass('stacked').css({'margin-top':-popup.target.outerHeight()/2, 'top':'50%'});
                }

                popup.minWidth = $(popup.html).hasClass('needpopup-overflow');
                if (popup.minWidth + 30 >= popup.window.innerWidth) {
                    $(popup.html).addClass('needpopup-overflow');
                }
                else {
                    $(popup.html).removeClass('needpopup-overflow');
                }
            }
        },

        'config' : {
            'default' : {
                'removerPlace' : 'inside',
                'closeOnOutside' : true,
                onShow : function() {},
                onBeforeShow: function() {},
                onHide: function() {}
            }
        }                
    
    }
})();