import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './event-photos.reducer';
import { IEventPhotos } from 'app/shared/model/event-photos.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEventPhotosDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class EventPhotosDetail extends React.Component<IEventPhotosDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { eventPhotosEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EventPhotos [<b>{eventPhotosEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="eventid">Eventid</span>
            </dt>
            <dd>{eventPhotosEntity.eventid}</dd>
            <dt>
              <span id="imageUrls">Image Urls</span>
            </dt>
            <dd>{eventPhotosEntity.imageUrls}</dd>
            <dt>
              <span id="likes">Likes</span>
            </dt>
            <dd>{eventPhotosEntity.likes}</dd>
            <dt>
              <span id="seen">Seen</span>
            </dt>
            <dd>{eventPhotosEntity.seen}</dd>
          </dl>
          <Button tag={Link} to="/entity/event-photos" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/event-photos/${eventPhotosEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ eventPhotos }: IRootState) => ({
  eventPhotosEntity: eventPhotos.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EventPhotosDetail);
