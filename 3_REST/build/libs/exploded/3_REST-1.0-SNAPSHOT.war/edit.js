function editMatch() {
    var match = {};

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

    var id = document.cookie;

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/match/" + id,
        data: JSON.stringify(match),
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });
}