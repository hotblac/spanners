var WebSocketServer = require('ws').Server
  , wss = new WebSocketServer({ port: 9090 });
var express = require('express');
var app = express();

// Start REST server on port 8081
var server = app.listen(8081, function () {
  var host = server.address().address
  var port = server.address().port
  console.log("Websocket event broadcaster REST API listening on http://%s:%s", host, port)
});

// Broadcast updates to all WebSocketServer clients
app.post('/notify/spanner/:spanner_id/update', function (req, res) {
   var spanner_id = req.params.spanner_id;
   console.log('Event: spanner %s updated', spanner_id);
   wss.clients.forEach(function each(client) {
      client.send("broadcast: spanner " + spanner_id + " updated");
    });
   res.sendStatus(200);
});

app.post('/notify/spanner/:spanner_id/delete', function (req, res) {
   var spanner_id = req.params.spanner_id;
   console.log('Event: spanner %s deleted', spanner_id);
   wss.clients.forEach(function each(client) {
      client.send("broadcast: spanner " + spanner_id + " deleted");
    });
   res.sendStatus(200);
});

app.post('/notify/spanner/:spanner_id/add', function (req, res) {
   var spanner_id = req.params.spanner_id;
   console.log('Event: spanner %s added', spanner_id);
   wss.clients.forEach(function each(client) {
      client.send("broadcast: spanner " + spanner_id + " added");
    });
   res.sendStatus(200);
});

