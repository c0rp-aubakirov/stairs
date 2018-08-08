var text = "<tr>\n" +
  "            <td>{ARRAY}</td>\n" +
  "            <td>{ANSWER}</td>\n" +
  "            <td>{ALGORITHM}</td>\n" +
  "        </tr>";

$("#target").submit(function (event) {
  event.preventDefault();

  var algo = $("#type").val();
  var term = $("#search").val();

  var re = new RegExp("^(\\d,?)*$");
  if (re.test(term)) {
    var in_data = JSON.stringify(term.split(',').map(Number));

    $.ajax({
      url: "api/stairsArray/?type=" + algo,
      type: "POST",
      data: in_data,
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function (data) {
        console.log(data);


        var card = String(text)
          .replace(/\{ARRAY}/g, in_data)
          .replace(/\{ANSWER}/g, data)
          .replace(/\{ALGORITHM}/g, algo);
        $('#container').prepend(card);
      },
    })

  } else {
    var card = String(text)
      .replace(/\{ARRAY}/g, $("#search").val())
      .replace(/\{ANSWER}/g, "Wrong input, enter integers delimited by ','")
  .replace(/\{ALGORITHM}/g, "NONE");
    $('#container').prepend(card);
  }
});

