function trackScrollHeight() {
    const scrollHeight = document.documentElement.scrollHeight;
    return scrollHeight;
} 
  
function changeMenuOverlayHeight(){
    const containerHeight = $('.categories__list-module').height();
    
    let requireHeght =  (trackScrollHeight() - containerHeight) - ( (trackScrollHeight() - containerHeight) / 100 * 4.2 );

    $('.categories__list-module').css('--dynamic-height', requireHeght  + 'px');    
}

window.addEventListener('resize', changeMenuOverlayHeight());