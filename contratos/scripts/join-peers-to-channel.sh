#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

export CHANNEL_NAME=providerschannel
export ORG_NAME=hospital1.example.com
export MSPID="Hospital1MSP"
CORE_PEER_MSPCONFIGPATH=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/$ORG_NAME/users/Admin@$ORG_NAME/msp
CORE_PEER_LOCALMSPID=$MSPID

function joinChannel() {
    PEER_NAME=$1
    CORE_PEER_ADDRESS=$PEER_NAME:7051
    CORE_PEER_TLS_ROOTCERT_FILE=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/$ORG_NAME/peers/$PEER_NAME/tls/ca.crt
    peer channel join -b ./channel-artifacts/$CHANNEL_NAME.block
}

joinChannel "P874LG"
joinChannel "P09Q6Y"
joinChannel "P60CC5"
joinChannel "P30KEH"
joinChannel "P51VDL"
