import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './media.reducer';
import { IMedia } from 'app/shared/model/media.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMediaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class MediaDetail extends React.Component<IMediaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { mediaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Media [<b>{mediaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="mediaType">Media Type</span>
            </dt>
            <dd>{mediaEntity.mediaType}</dd>
            <dt>
              <span id="createdOn">Created On</span>
            </dt>
            <dd>
              <TextFormat value={mediaEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="fileUrl">File Url</span>
            </dt>
            <dd>{mediaEntity.fileUrl}</dd>
            <dt>
              <span id="title">Title</span>
            </dt>
            <dd>{mediaEntity.title}</dd>
          </dl>
          <Button tag={Link} to="/entity/media" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/media/${mediaEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ media }: IRootState) => ({
  mediaEntity: media.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MediaDetail);
