$(document).ready(function () {

    // variables 
    let isHovered = false;
    
    // categories
    const categoryList = [ 'accessories', 'sparepart', 'bicycle', 'electro', 'equipment' ];
 
    // functions
    function makeEvents(item){
        $(`#${item}`).on('mouseenter', function(){
            if(!isHovered) enableMenuVisibility(item);
        }).on('mouseleave', function(){
            if($(`.categories__module-container:hover`).length === 0) disableMenuVisibility(item)
        });
    
        $(`.categories__module-container`).on('mouseleave', function(){
            if($(`#${item}:hover`).length === 0) disableMenuVisibility(item)
        });
    }

    function enableMenuVisibility(item){
        $(`.categories__list-module.${item}`).stop(true, true).fadeIn(200);
        changeSpanColor('rgb(0, 136, 214)', item);    
        isHovered = true;
    }

    function disableMenuVisibility(item){
        $(`.categories__list-module.${item}`).stop(true, true).fadeOut(250);
        changeSpanColor('black', item);
        isHovered = false; 
    }

    function changeSpanColor(color, item){
        $(`#${item} span`).css('color', color);
    }

    // Action
    categoryList.forEach(makeEvents);
    $("a").attr("href", "./product.html");
});