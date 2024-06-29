from telethon import TelegramClient, events, sync
from telethon.tl.types import InputChannel, PeerChannel
from telethon.tl.types import Channel
import time
import json

# Get your own api_id and
# api_hash from https://my.telegram.org, under API Development.
#  or from https://tjhorner.dev/webogram/#/login
api_id = 27622888
api_hash = '915cd636ba71e15ffe0c2eaf15c44837'

GROUP_CHAT_ID = 1543855929

client = TelegramClient('session_name', api_id, api_hash)
client.start()

group = client.get_entity(PeerChannel(GROUP_CHAT_ID))

c = 0
m = 0
with open("run1/deleted_messages.json","w") as _file: 
    _file.write('{"messages":[')
    for event in client.iter_admin_log(group):
        if event.deleted_message:
            print("Dumping message",c, "(", event.old.id, event.old.date,")")
            _file.write(event.old.to_json() + ",") 
            c+=1
            if event.old.media:
                m+=1
                #print(event.old.media.to_dict()['Document']['id'])
                client.download_media(event.old.media, str(event.old.id))
                print(" Dumped media", m)
            time.sleep(0.1)
    _file.write('{}]}')
