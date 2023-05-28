#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

export CHANNEL_NAME=providerschannel
export ORDERER_NAME=orderer0.hospital1.example.com

export ORG_NAME=hospital1.example.com
export PEER_NAME=P874LG
export MSPID="Hospital1MSP"
export PANCHORS=Hospital1MSPanchors
export CORE_PEER_MSPCONFIGPATH=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/$ORG_NAME/users/Admin@$ORG_NAME/msp
export CORE_PEER_ADDRESS=$PEER_NAME:7051
export CORE_PEER_LOCALMSPID=$MSPID
export CORE_PEER_TLS_ROOTCERT_FILE=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/$ORG_NAME/peers/$PEER_NAME/tls/ca.crt
peer channel update -o $ORDERER_NAME:7050 -c $CHANNEL_NAME -f ./channel-artifacts/$PANCHORS.tx --tls --cafile /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/hospital1.example.com/peers/$ORDERER_NAME/msp/tlscacerts/tlsca.hospital1.example.com-cert.pem