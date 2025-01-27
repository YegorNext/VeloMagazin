$(document).ready(function () {

    // variables 
    let isHovered = false;
    
    var categoryList = [ $('#accessories'), $('#sparepart'), $('#bicycle'), $('#electro'), $('#equipment') ];

    // functions
    function enableMenuVisibility(){
        $('.categories__list-module').stop(true, true).fadeIn(200);
        changeSpanColor('rgb(0, 136, 214)');    
        isHovered = true;
    }

    function disableMenuVisibility(){
        $('.categories__list-module').stop(true, true).fadeOut(250);
        changeSpanColor('black');
        isHovered = false; 
    }

    function changeSpanColor(color){
        $('#accessories span').css('color', color);
    }

    // events
    $('#accessories').on('mouseenter', function(){
        if(!isHovered) enableMenuVisibility();
    }).on('mouseleave', function(){
        if($('.categories__module-container:hover').length === 0) disableMenuVisibility()
    });

    $('.categories__module-container').on('mouseleave', function(){
        if($('#accessories:hover').length === 0) disableMenuVisibility()
    });
});