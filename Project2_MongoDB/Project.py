#Pratik Barhate
#Sneha Srinivas
import json
from pymongo import MongoClient

client = MongoClient('localhost', 27017)
db = client.WORLDCUP

result = db["TEAM_SCORE"]

list1 = []
# scores ={}
teams = db.TEAM.find()

for team in teams:
    scores =  {} 
    score = []
    tname = team['Team']
    games = db.GAME.find()
    for game in games:
    #team1 = db.Teams.find({"TeamID": game['TeamID1']})[0]
        if game['TeamID1'] == team['TeamID']:
            team2 = db.TEAM.find({"TeamID": game['TeamID2']})[0]
            stadium = db.STADIUM.find({"SID": game['SID']})[0]
            score.append({'GameID': game['GameID'],'Team1': team['Team'], 'Team2': team2['Team'], 'MatchDate': game['MatchDate'], 'Stadium': stadium['SName'], 'SCity': stadium['SCity'], 'Team1_Score': game['Team1_Score'], 'Team2_Score': game['Team2_Score'] } )
        elif game['TeamID2'] == team['TeamID']:
            team2 = db.TEAM.find({"TeamID": game['TeamID1']})[0]
            stadium = db.STADIUM.find({"SID": game['SID']})[0]
            score.append({'GameID': game['GameID'],'Team1': team['Team'], 'Team2': team2['Team'], 'MatchDate': game['MatchDate'], 'Stadium': stadium['SName'], 'SCity': stadium['SCity'], 'Team1_Score': game['Team1_Score'], 'Team2_Score': game['Team2_Score'] } )
        else: continue
            
#    if len(score) != 0:
    scores["Team"] = tname
    scores["matches"] = score
    list1.append(scores)
    print(list1)
jsonStr = json.dumps(scores, sort_keys=True,indent=4, separators=(',', ': '))
TeamScores = json.loads(jsonStr)
print(jsonStr)

result.insert_many(list1)

with open('TEAM_SCORES.json', 'w') as f:
    json.dump(TeamScores, f)
#
#players = db.Players.find()