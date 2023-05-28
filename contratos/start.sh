#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

export PATH=C:/Users/gabri/projetos/fabric-samples/bin:$PATH
export FABRIC_CFG_PATH=${PWD}
export CHANNEL_NAME=providerschannel
export COMPOSE_PROJECT_NAME=ehrnetwork

cryptogen generate --config=./crypto-config.yaml

configtxgen -profile InitialOrgsOrdererGenesis -outputBlock ./channel-artifacts/genesis.block -channelID ehrnetwork-sys-channel
configtxgen -profile InitialOrgsChannel -outputCreateChannelTx ./channel-artifacts/${CHANNEL_NAME}.tx -channelID $CHANNEL_NAME
configtxgen -profile InitialOrgsChannel -outputAnchorPeersUpdate ./channel-artifacts/Hospital1MSPanchors.tx -channelID $CHANNEL_NAME -asOrg Hospital1MSP

./scripts/config-files.sh

docker-compose up -d

sleep 20s

docker exec -i cli0 bash < scripts/create-channel-request.sh &> /dev/null
docker exec -i cli0 bash < scripts/join-peers-to-channel.sh &> /dev/null
docker exec -i cli0 bash < scripts/define-anchor-peers.sh &> /dev/null
docker-compose -f monitoring/compose-monitoring.yml up -d
docker exec -i cli0 bash < scripts/instantiate-chaincode.sh &> /dev/null