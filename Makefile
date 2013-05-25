all: configure build

env-setup: configure db-create gf-create-domain gf-start-domain gf-configure-domain
env-recreate: gf-delete-domain db-drop env-setup

configure:
	scripts/scripts.sh configure

db-create:
	scripts/scripts.sh db_create
db-drop:
	scripts/scripts.sh db_drop
db-start:
	scripts/scripts.sh db_start
db-stop:
	scripts/scripts.sh db_stop

gf-create-domain:
	scripts/scripts.sh gf_create_domain
gf-delete-domain:
	scripts/scripts.sh gf_delete_domain
gf-start-domain:
	scripts/scripts.sh gf_start_domain
gf-stop-domain:
	scripts/scripts.sh gf_stop_domain
gf-configure-domain:
	scripts/scripts.sh gf_configure_domain

build:
	scripts/scripts.sh build
