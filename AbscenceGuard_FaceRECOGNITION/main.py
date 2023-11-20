import cv2
import face_recognition
import os

import mysql.connector
import numpy as np
from datetime import datetime

path = 'images'
images = []
classNames = []
myList = os.listdir(path)

for cl in myList:
    curImg = cv2.imread(f'{path}/{cl}')
    images.append(curImg)
    classNames.append(os.path.splitext(cl)[0])

def findEncodings(images):
    encodeList = []
    for img in images:
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        encode = face_recognition.face_encodings(img)[0]
        encodeList.append(encode)
    return encodeList

def markAttendance(name):
    # Replace these with your actual database credentials
    db_config = {
        "host": "localhost",
        "user": "root",
        "password": "",
        "database": "stage"
    }

    try:
        # Create a connection to the database
        conn = mysql.connector.connect(**db_config)

        if conn.is_connected():
            print("Connected to MySQL database!")

            # Check if the person's attendance is not already recorded in the database
            cursor = conn.cursor()
            query = "SELECT name FROM abs WHERE name = %s"
            cursor.execute(query, (name,))
            result = cursor.fetchall()
            query2 = "SELECT immatr FROM employee WHERE name = %s"
            cursor.execute(query2, (name,))
            immatr_result=cursor.fetchall()

            print(immatr_result)

            if not result:
                # Insert the attendance record into the database
                now = datetime.now()
                time_str = now.strftime('%H:%M')
                date_str = now.strftime(' %d-%B-%Y')
                full_date=time_str+date_str

                insert_query = "INSERT INTO abs (name, date_arriv) VALUES (%s,  %s)"
                cursor.execute(insert_query, (name,full_date))
                update = "UPDATE abs SET immatr = %s WHERE name = %s"
                immatr_data = [row[0] for row in immatr_result]
                date=','.join(immatr_data)
                cursor.execute(update, (date,name))
                conn.commit()

                print(f"{name} attendance recorded: {full_date} ")
            else:print('already exist')

    except mysql.connector.Error as e:
        print("Error connecting to MySQL database:", e)

    finally:
        # Close the database connection
        if 'conn' in locals() and conn.is_connected():
            conn.close()
            print("Connection to MySQL database closed.")


def recognizeFaces():
    encodeListKnown = findEncodings(images)
    print('Encoding Complete')

    cap = cv2.VideoCapture(0)

    while True:
        success, img = cap.read()
        imgS = cv2.resize(img, (0, 0), None, 0.25, 0.25)
        imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)

        facesCurFrame = face_recognition.face_locations(imgS)
        encodesCurFrame = face_recognition.face_encodings(imgS, facesCurFrame)

        for encodeFace, faceLoc in zip(encodesCurFrame, facesCurFrame):
            matches = face_recognition.compare_faces(encodeListKnown, encodeFace)
            faceDis = face_recognition.face_distance(encodeListKnown, encodeFace)
            matchIndex = np.argmin(faceDis)

            if matches[matchIndex]:
                name = classNames[matchIndex].upper()
                markAttendance(name)
            else:
                name = 'Unknown'

            y1, x2, y2, x1 = faceLoc
            y1, x2, y2, x1 = y1*4, x2*4, y2*4, x1*4
            cv2.rectangle(img, (x1, y1), (x2, y2), (0, 255, 0), 2)
            cv2.rectangle(img, (x1, y2-35), (x2, y2), (0, 255, 0), cv2.FILLED)
            cv2.putText(img, name, (x1+6, y2-6), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 255, 255), 2)

        cv2.imshow('Webcam', img)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()

recognizeFaces()