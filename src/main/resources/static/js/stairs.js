
var text = "<tr>\n" +
  "            <td>{ARRAY}</td>\n" +
  "            <td>{ANSWER}</td>\n" +
  "        </tr>";

$( "#target" ).submit(function( event ) {
  event.preventDefault();

  var term = $("#search").val();
  var re = new RegExp("^(\\d,?)*$");
  if (re.test(term)) {
    var data = JSON.stringify($("#search").val().split(',').map(Number));
    console.log(data);

    $.ajax({
      url:"api/stairsArray/?type="+$("#type").val(),
      type:"POST",
      data:data,
      contentType:"application/json; charset=utf-8",
      dataType:"json",
      success: function(data){
        console.log(data);


        var card = String(text)
          .replace(/\{ARRAY}/g, $("#search").val())
          .replace(/\{ANSWER}/g, data);
        $('#container').prepend(card);
      },
    })

  } else {
    var card = String(text)
      .replace(/\{ARRAY}/g, $("#search").val())
      .replace(/\{ANSWER}/g, "Wrong input, enter integers delimited by ','");
    $('#container').prepend(card);
  }
});

