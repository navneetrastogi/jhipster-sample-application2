import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './event-photos.reducer';
import { IEventPhotos } from 'app/shared/model/event-photos.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEventPhotosProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EventPhotos extends React.Component<IEventPhotosProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { eventPhotosList, match } = this.props;
    return (
      <div>
        <h2 id="event-photos-heading">
          Event Photos
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Event Photos
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Eventid</th>
                <th>Image Urls</th>
                <th>Likes</th>
                <th>Seen</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {eventPhotosList.map((eventPhotos, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${eventPhotos.id}`} color="link" size="sm">
                      {eventPhotos.id}
                    </Button>
                  </td>
                  <td>{eventPhotos.eventid}</td>
                  <td>{eventPhotos.imageUrls}</td>
                  <td>{eventPhotos.likes}</td>
                  <td>{eventPhotos.seen}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${eventPhotos.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${eventPhotos.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${eventPhotos.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ eventPhotos }: IRootState) => ({
  eventPhotosList: eventPhotos.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EventPhotos);
