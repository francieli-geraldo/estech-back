#!/bin/bash

######################################################
#  Script Written by : Rahul Kumar
#  Date: Feb 21, 2013
######################################################

DATE=`date +%d%b%y`
LOCAL_BACKUP_DIR="/tmp/backup/"
DB_NAME="afinese-prd"
DB_USER="root"
DB_PASSWORD="root"
FTP_SERVER="ftp.scsoftware.com.br"
FTP_USERNAME="scsoftwa"
FTP_PASSWORD="scr$31"
FTP_UPLOAD_DIR="/backups/"
LOG_FILE=$FTP_UPLOAD_DIR/$DB_NAME-$DATE.log

############### Local Backup  ########################

sudo docker exec mysql /usr/bin/mysqldump --user=$DB_USER --password=$DB_PASSWORD $DB_NAME | gzip  > $LOCAL_BACKUP_DIR/$DB_NAME-$DATE.sql.gz

############### UPLOAD to FTP Server  ################

# ftp -n $FTP_SERVER << EndFTP
# user "$FTP_USERNAME" "$FTP_PASSWORD"
# binary
# hash
# cd $FTP_UPLOAD_DIR
#pwd
# lcd $LOCAL_BACKUP_DIR
# put "$DB_NAME-$DATE.sql.gz"
# bye
# EndFTP

# if test $? = 0
# then
#     echo "Database Successfully Uploaded to Ftp Server
#         File Name $DB_NAME-$DATE.sql.gz " > $LOG_FILE
# else
#     echo "Error in database Upload to Ftp Server" > $LOG_FILE
# fi
