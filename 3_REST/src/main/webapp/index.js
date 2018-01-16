function showAdd() {
    $(document).ready(function(){
        $("#add").click(function(){
            $("#main").load("/add.html");
        });
    });

}

function showSearch() {
    $(document).ready(function(){
        $("#search").click(function(){
            $("#main").load("/search.html");
        });
    });
}

function download() {

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/match/download"
        });
}