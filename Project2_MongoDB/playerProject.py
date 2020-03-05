#Pratik Barhate
from pymongo import MongoClient
client = MongoClient('localhost', 27017)
db = client.WORLDCUP

playerData = []
players = db.PLAYER.find()
res = db['PLAYER_DATA']

for player in players:
    data = {}
    record = []
    lineups = db.STARTING_LINEUP.find({'PlayerID': player['PlayerID']})
    if lineups != 0:
        for lineup in lineups:
            game = db.GAME.find({'GameID': lineup['GameID']})[0]
            stadium = db.STADIUM.find({"SID": game['SID']})[0]
            record.append({'MatchDate': game['MatchDate'], 'Stadium': stadium['SName'], 'SCity': stadium['SCity']})
    data[player['FIFA Popular Name']] = {'TeamName': player['Team'], 'PNo': player['PlayerID'], 'Position': player['Position'], 'Games': record}
    playerData.append(data)

#print(playerData)
res.insert_many(playerData)

#jsonDataStr = json.dumps(data, sort_keys=True,indent=4, separators=(',', ': '))
#playerData = json.loads(jsonDataStr)
    #substitution = db.Substitution.find({'Substitute_PlayerID': player['PlayerID']})
#with open('PLAYER_DATA.json', 'w') as f:
    #json.dump(playerData, f)
#print(jsonDataStr)