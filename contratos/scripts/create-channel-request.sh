#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

export CHANNEL_NAME=providerschannel
export ORDERER_NAME=orderer0.hospital1.example.com
BLOCKFILE="./channel-artifacts/${CHANNEL_NAME}.block"

peer channel create -o $ORDERER_NAME:7050 -c $CHANNEL_NAME -f ./channel-artifacts/$CHANNEL_NAME.tx --outputBlock $BLOCKFILE --tls --cafile /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/hospital1.example.com/peers/$ORDERER_NAME/msp/tlscacerts/tlsca.hospital1.example.com-cert.pem
