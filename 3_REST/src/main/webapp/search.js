function searchByFields() {

    var match = {};
    var $url = "?";

    match["date"] = $("#date").val();
    match["time"] = $("#time").val();
    match["venue"] = $("#venue").val();
    match["competition"] = $("#competition").val();
    match["homeTeam"] = $("#homeTeam").val();
    match["awayTeam"] = $("#awayTeam").val();
    match["homeTeamAbbr"] = $("#homeTeamAbbr").val();
    match["awayTeamAbbr"] = $("#awayTeamAbbr").val();
    match["halfLength"] = $("#halfLength").val();
    match["playersNumber"] = $("#playersNumber").val();
    match["subsNumber"] = $("#subsNumber").val();


    if (match["date"])
        $url += "&date=" + match["date"];
    if (match["time"])
        $url += "&time=" + match["time"];
    if (match["venue"])
       $url += "&venue=" + match["venue"];
    if (match["competition"])
        $url += "&competition=" + match["competition"];
    if (match["homeTeam"])
        $url += "&homeTeam=" + match["homeTeam"];
    if (match["awayTeam"])
        $url += "&awayTeam=" + match["awayTeam"];
    if (match["awayTeamAbbr"])
        $url += "&awayTeamAbbr=" + match["awayTeamAbbr"];
    if (match["homeTeamAbbr"])
        $url += "&homeTeamAbbr=" + match["homeTeamAbbr"];
    if (match["halfLength"])
        $url += "&halfLength=" + match["halfLength"];
    if (match["playersNumber"])
        $url += "&playersNumber=" + match["playersNumber"];
    if (match["subsNumber"])
        $url += "&subsNumber=" + match["subsNumber"];

    alert($url);
    $('table').empty();

    $('table').append("<tr><td>"+"ID"+"</td>"
        +"<td>"+"Date"+"</td>"
        +"<td>"+"Time"+"</td>"
        +"<td>"+"Venue"+"</td>"
        +"<td>"+"Competition"+"</td>"
        +"<td>"+"Home"+"</td>"
        +"<td>"+"Away"+"</td>"
        +"<td>"+"Home abbr."+"</td>"
        +"<td>"+"Away abbr."+"</td>"
        +"<td>"+"Half length"+"</td>"
        +"<td>"+"№ of players"+"</td>"
        +"<td>"+"№ of subs"+"</td>"
        +"<td>"+"Edit"+"</td>"
        +"<td>"+"Delete"+"</td>"
        +"</tr>");

    $.ajax({
        type: 'GET',
        url: '/api/match/byFields' + $url,
        data: $url,
        dataType: 'json',
        success: function (data) {
            $.each(data, function(index, element) {
                $('table').append($('<tr>'));
                $('table').append($('<td>', {
                    text: element.id,
                }));

                $('table').append($('<td>', {
                    text: element.date,
                }));
                $('table').append($('<td>', {
                    text: element.time,
                }));
                $('table').append($('<td>', {
                    text: element.venue,
                }));
                $('table').append($('<td>', {
                    text: element.competition,
                }));
                $('table').append($('<td>', {
                    text: element.homeTeam,
                }));
                $('table').append($('<td>', {
                    text: element.awayTeam,
                }));
                $('table').append($('<td>', {
                    text: element.homeTeamAbbr,
                }));
                $('table').append($('<td>', {
                    text: element.awayTeamAbbr,
                }));
                $('table').append($('<td>', {
                    text: element.halfLength,
                }));
                $('table').append($('<td>', {
                    text: element.playersNumber,
                }));
                $('table').append($('<td>', {
                    text: element.subsNumber,
                }));
                $('table').append($('<td><button id="edit" type="button" onclick="edit()">Edit</button>'));

                $('table').append($('<td><button id="delete" type="button" onclick="del()">Delete</button>'));
                $('table').append($('</tr>'));
            });
        }
    });
}

function searchAll() {

    $('table').empty();

    $('table').append("<tr><td>"+"ID"+"</td>"
        +"<td>"+"Date"+"</td>"
        +"<td>"+"Time"+"</td>"
        +"<td>"+"Venue"+"</td>"
        +"<td>"+"Competition"+"</td>"
        +"<td>"+"Home"+"</td>"
        +"<td>"+"Away"+"</td>"
        +"<td>"+"Home abbr."+"</td>"
        +"<td>"+"Away abbr."+"</td>"
        +"<td>"+"Half length"+"</td>"
        +"<td>"+"№ of players"+"</td>"
        +"<td>"+"№ of subs"+"</td>"
        +"<td>"+"Edit"+"</td>"
        +"<td>"+"Delete"+"</td>"
        +"</tr>");

    $.ajax({
        type: 'GET',
        url: '/api/match/all',
        data: { get_param: 'value' },
        dataType: 'json',
        success: function (data) {
            $.each(data, function(index, element) {
                $('table').append($('<tr>'));
                $('table').append($('<td>', {
                    text: element.id,
                }));

                $('table').append($('<td>', {
                    text: element.date,
                }));
                $('table').append($('<td>', {
                    text: element.time,
                }));
                $('table').append($('<td>', {
                    text: element.venue,
                }));
                $('table').append($('<td>', {
                    text: element.competition,
                }));
                $('table').append($('<td>', {
                    text: element.homeTeam,
                }));
                $('table').append($('<td>', {
                    text: element.awayTeam,
                }));
                $('table').append($('<td>', {
                    text: element.homeTeamAbbr,
                }));
                $('table').append($('<td>', {
                    text: element.awayTeamAbbr,
                }));
                $('table').append($('<td>', {
                    text: element.halfLength,
                }));
                $('table').append($('<td>', {
                    text: element.playersNumber,
                }));
                $('table').append($('<td>', {
                    text: element.subsNumber,
                }));
                var currentID = element.id;


                var tmpButton = $('<td><button id="edit" type="button" >Edit</button>');
                tmpButton.on("click", {id:currentID}, function(event){

                    edit(event);
                });
                $('table').append(tmpButton);
                tmpButton=$('<td><button id="delete" type="button" >Delete</button>');
                tmpButton.on("click", {id:currentID}, function(event){

                    del(event);
                });
                $('table').append(tmpButton);

                $('table').append($('</tr>'));
            });
        }
    });
}

function edit(event) {

    document.cookie = event.data.id;

    $(document).ready(function(){
        $("#edit").click(function(){
            $("#main").load("/edit.html");
        });
    });
}

function del(event) {

    //alert(event.data.id);
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/match/"+ event.data.id,
    });
}
