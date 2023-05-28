#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

rm -fr crypto-config
rm -fr channel-artifacts
docker stop $(docker ps -aq) && docker rm $(docker ps -aq) && docker volume prune -f
docker rmi $(docker images |grep 'dev') &> /dev/null
