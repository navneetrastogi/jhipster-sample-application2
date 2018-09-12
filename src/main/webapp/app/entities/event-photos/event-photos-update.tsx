import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './event-photos.reducer';
import { IEventPhotos } from 'app/shared/model/event-photos.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEventPhotosUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IEventPhotosUpdateState {
  isNew: boolean;
}

export class EventPhotosUpdate extends React.Component<IEventPhotosUpdateProps, IEventPhotosUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { eventPhotosEntity } = this.props;
      const entity = {
        ...eventPhotosEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/event-photos');
  };

  render() {
    const { eventPhotosEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplication2App.eventPhotos.home.createOrEditLabel">Create or edit a EventPhotos</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : eventPhotosEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="event-photos-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="eventidLabel" for="eventid">
                    Eventid
                  </Label>
                  <AvField id="event-photos-eventid" type="number" className="form-control" name="eventid" />
                </AvGroup>
                <AvGroup>
                  <Label id="imageUrlsLabel" for="imageUrls">
                    Image Urls
                  </Label>
                  <AvField id="event-photos-imageUrls" type="text" name="imageUrls" />
                </AvGroup>
                <AvGroup>
                  <Label id="likesLabel" for="likes">
                    Likes
                  </Label>
                  <AvField id="event-photos-likes" type="text" name="likes" />
                </AvGroup>
                <AvGroup>
                  <Label id="seenLabel" for="seen">
                    Seen
                  </Label>
                  <AvField id="event-photos-seen" type="text" name="seen" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/event-photos" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  eventPhotosEntity: storeState.eventPhotos.entity,
  loading: storeState.eventPhotos.loading,
  updating: storeState.eventPhotos.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EventPhotosUpdate);
